package com.learn.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.stereotype.Service;

@Service
public class TextByteConverter {


	public byte[] objectToByteArray(Object pageSegmentMap) {
		ByteArrayOutputStream segmentOut = new ByteArrayOutputStream();
		ObjectOutputStream segmentOs = null;
		try {
			segmentOs = new ObjectOutputStream(segmentOut);
			segmentOs.writeObject(pageSegmentMap);
			segmentOs.close();
			byte[] output = getCompressData(segmentOut.toByteArray());
			segmentOut.close();
			return output;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (segmentOs != null) {
					segmentOs.close();
				}
				if (segmentOut != null) {
					segmentOut.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Object byteArrayToObject(byte[] byteArray) {
		ByteArrayInputStream in = new ByteArrayInputStream(decompress(byteArray));
		ObjectInputStream is = null;
		try {
			is = new ObjectInputStream(in);
			return is.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public byte[] getCompressData(byte[] data) throws IOException {
		byte[] output = null;
		Deflater deflater = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
			deflater = new Deflater();
			deflater.setInput(data);
			deflater.finish();
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer); // returns the generated code... index
				outputStream.write(buffer, 0, count);
			}
			output = outputStream.toByteArray();
		} finally {
			if (deflater != null) {
				deflater.end();
			}
		}
		return output;
	}

	public byte[] decompress(byte[] data) {
		Inflater inflater = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length)) {
			inflater = new Inflater();
			inflater.setInput(data);
			byte[] buffer = new byte[1024];
			while (!inflater.finished()) {
				int count;
				count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);
			}
			byte[] output = outputStream.toByteArray();
			return output;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inflater != null) {
				inflater.end();
			}
		}
		return data;
	}
}
