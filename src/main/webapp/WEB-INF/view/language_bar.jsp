<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<header>
  <div>
    <ul>
      <li><a href="${request.contextPath}?lang=en"><spring:message code="language.en"/> </a> </li>
      <li><a href="${request.contextPath}?lang=ru"><spring:message code="language.ru"/></a> </li>
    </ul>
  </div>
</header>
