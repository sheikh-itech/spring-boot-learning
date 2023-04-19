package com.learn.util;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Component
public class CommonUtil {

	private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	
	private Set<String> optFields = new HashSet<>();
	private Set<String> totalKeys = new HashSet<>();
	
	public CommonUtil() {
		optFields.add("advRequestOn");
		optFields.add("advResponseOn");
		optFields.add("requestSentOn");
		optFields.add("responseSentOn");
		optFields.add("requestOn");
		optFields.add("responseOn");
		this.initKeys();
	}
	
	/**
	 * @param bean object need to check for SQL Injection Possibility
	 * @return Map<String, String> holds method name and it's returned value<br />
	 *         If Returned Map is empty that means 'No Sql Injection' found
	 */
	public Map<String, String> validateInputObject(AuthBean bean) {

		Map<String, String> outcome = new HashMap<>();
		
		validateInnerObjects(bean, outcome);
		if(outcome.size()>0)
			return outcome;
		
		checkSqlInjection(bean, outcome);

		return outcome;
	}

	public Set<String> allKeysExist(Set<String> keys) {

		if(this.totalKeys.size()==keys.size() && this.totalKeys.containsAll(keys))
			return new HashSet<String>();
		else{
			/*
			totalKeys.removeAll(keys);
			Set<String> outcome = totalKeys.stream().map(String::new).collect(Collectors.toSet());
			totalKeys.clear();
			this.initKeys();
			return outcome;
			*/
			keys.removeAll(totalKeys);
			return keys;
		}
	}
	
	private void validateInnerObjects(AuthBean bean, Map<String, String> outcome) {
		
		if(bean==null) {
			outcome.put("uses", null);
			return;
		}
	}
	
	private void validateFields(String fieldName, Object fieldValue, Map<String, String> outcome) {
		
		if(fieldValue==null && optFields.contains(fieldName))
			return;
		else if(fieldValue==null) {
			outcome.put(fieldName, fieldName);
			return;
		}
		
		String value = fieldValue.toString().trim();
		
		if(fieldName.startsWith("key1") && (!(value.length()==18 || value.isEmpty()) || !alphaNumeric(value)))
			outcome.put("key1", "missing key");
		if (fieldName.startsWith("key2") && !((value.length()<=10 && value.length()>=0) || !alphaNumeric(value)))
			outcome.put("key2", "missing key");
		if (fieldName.startsWith("value1") && !((value.length()<=10 && value.length()>=0) || !alphaNumeric(value)))
			outcome.put("value1", "value1");
	}
	
	private boolean alphaNumeric(String text) {
		
		if("".equals(text))
			return true;
		
		return text.matches("^[\\pL\\pN]+$");
	}
	
	private boolean validAlphaNum(String text) {
		
		if("".equals(text))
			return true;
		
		return text.matches("^[\\pL\\pN\\.\\:\\_]+$");
	}
	
	private void checkSqlInjection(Object bean, Map<String, String> outcome) {

		try {

			if (bean == null)
				return;

			Method[] a = bean.getClass().getMethods();
			for (Method method : a) {

				if (isGetter(method)) {
					
					if (chechReturnType(method.getReturnType().getName()))
						checkSqlInjection(method.invoke(bean), outcome);

					Object value = method.invoke(bean);

					String field = null;
					if (method.getName().startsWith("get"))
						field = method.getName().substring(3);
					else if (method.getName().startsWith("is"))
						field = method.getName().substring(2);
					else
						continue;
					
					field = StringUtils.uncapitalize(field);
					
					validateFields(field, value, outcome);
					
					if (value!=null && isSQLInjection(value) && validAlphaNum(value.toString()))
						outcome.put(field, "Sql Injection Possibility");
				}
			}
		} catch (Exception ex) {
			logger.error("Sql Injection Check error, "+ex.getMessage());
		}
	}

	private boolean chechReturnType(String name) {

		return name.startsWith("com");
	}

	private boolean isGetter(Method method) {

		if (method.getName().startsWith("get") || method.getName().startsWith("is"))
			return true;

		return false;
	}

	private boolean isSQLInjection(Object object) {

		String REGEX = "\\s*and\\s*|\\s*or\\s*|\\s*between\\*+|\\s*union\\s*|\\s*join\\s*|\\s*sleep\\s*|\\s*shutdown\\s*|\\s*select\\s*|\\s*drop\\s*|\\s*delete\\s*|[\\(\\)<>;'\"`#=%*]|[-]{2,}/i";
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(object.toString().trim().toLowerCase());

		if (m.matches())
			return true;
		else
			return false;
	}
	
	public void resolveKeys(String body, List<String> keys, ObjectMapper mapper)
			throws JsonMappingException, JsonProcessingException {

		JsonNode jsonNode = mapper.readTree(body);
		getAllNestedFields(jsonNode, keys);
	}

	private void getAllNestedFields(JsonNode jsonNode, List<String> keys) {

		if (jsonNode.isObject()) {
			Iterator<Entry<String, JsonNode>> fields = jsonNode.fields();
			fields.forEachRemaining(field -> {
				keys.add(field.getKey());
				getAllNestedFields((JsonNode) field.getValue(), keys);
			});
		} else if (jsonNode.isArray()) {
			ArrayNode arrayField = (ArrayNode) jsonNode;
			arrayField.forEach(node -> {
				getAllNestedFields(node, keys);
			});
		}
	}
	
	private void initKeys() {
		
		this.totalKeys.add("uid");
		this.totalKeys.add("refKey");
		this.totalKeys.add("tid");
		this.totalKeys.add("transactionId");
		this.totalKeys.add("sa");
		this.totalKeys.add("domainId");
		this.totalKeys.add("lk");
		this.totalKeys.add("consent");
		this.totalKeys.add("deptCode");
		this.totalKeys.add("txn");
		this.totalKeys.add("ra");
		this.totalKeys.add("lData");
		this.totalKeys.add("pfr");
		this.totalKeys.add("disclosureInfo");
		this.totalKeys.add("uses");
		this.totalKeys.add("bio");
		this.totalKeys.add("pa");
		this.totalKeys.add("pfa");
		this.totalKeys.add("pi");
		this.totalKeys.add("pin");
		this.totalKeys.add("otp");
		this.totalKeys.add("bt");
		this.totalKeys.add("meta");
		this.totalKeys.add("udc");
		this.totalKeys.add("pidData");
		this.totalKeys.add("skey");
		this.totalKeys.add("ci");
		this.totalKeys.add("text");
		this.totalKeys.add("hmac");
		this.totalKeys.add("data");
		this.totalKeys.add("type");
		this.totalKeys.add("text");
		this.totalKeys.add("resp");
		this.totalKeys.add("fType");
		this.totalKeys.add("fCount");
		this.totalKeys.add("iType");
		this.totalKeys.add("iCount");
		this.totalKeys.add("pType");
		this.totalKeys.add("pCount");
		this.totalKeys.add("errCode");
		this.totalKeys.add("errInfo");
		this.totalKeys.add("nmPoints");
		this.totalKeys.add("qScore");
		this.totalKeys.add("deviceInfo");
		this.totalKeys.add("dpId");
		this.totalKeys.add("rdsId");
		this.totalKeys.add("rdsVer");
		this.totalKeys.add("dc");
		this.totalKeys.add("mi");
		this.totalKeys.add("mc");
		this.totalKeys.add("serviceType");
		this.totalKeys.add("ipAddress");
		this.totalKeys.add("platform");
		this.totalKeys.add("bioType");
		this.totalKeys.add("requestId");
		this.totalKeys.add("requestType");
		this.totalKeys.add("uidRefKey");
		this.totalKeys.add("isAadhar");
		this.totalKeys.add("advRequestOn");
		this.totalKeys.add("udc");
		this.totalKeys.add("uidEnc");
		this.totalKeys.add("udcCompl");
		this.totalKeys.add("advResponseOn");
		this.totalKeys.add("requestSentOn");
		this.totalKeys.add("responseSentOn");
		this.totalKeys.add("requestOn");
		this.totalKeys.add("responseOn");
		this.totalKeys.add("subAuaCode");
		this.totalKeys.add("asa");
		this.totalKeys.add("ttl");		
	}
}
