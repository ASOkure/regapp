<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
    


<s:bean name="java.util.HasMap" id = "qTabeleLayout">
<s:param name = "tablecolspan"  value="%{8}"/>
</s:bean>

<s:form action="complexForm.action" method="post" theme="qxhtml">
    <s:textfield label="SO Number" name="order.fromPartyOrderNumber"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{2}" /> 
    </s:textfield>
    <s:select name="salespersonId" label="Salesperson" list="genericList" listKey="id" listValue="name"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{2}" /> 
    </s:select>

    <s:textfield label="Trucker" name="order.dutyPrepaid" size="70"> 
        <s:param name="labelcolspan" value="%{2}" /> 
        <s:param name="inputcolspan" value="%{6}" /> 
    </s:textfield>

    <s:select name="newPoRequired" label="New PO Required" list="genericList"
               listKey="id" listValue="name" />
    <s:select name="shipdateConfirmed" label="Ship Date Confirmed" 
               list="genericList" listKey="id" listValue="name" />
    <s:select name="accounting90EntryDone" label="Acctg Entry Done" 
               list="genericList" listKey="id" listValue="name" />
    <s:select name="factored" label="Factored" list="genericList" listKey="id" 
               listValue="name" />

    <tr><th align="center" colspan="8">Line Items</th></tr>

    <tr>
        <th>#</th>
        <th>Product</th>
        <th>Qty</th>
        <th>Unit Price</th>
        <th>Allocation Instructions</th>
        <th>Label Instructions</th>
        <th>Description</th>
        <th>Override Reason</th>
    </tr>

    <s:iterator value="lineItems" status="status">
        <s:component template="/components/textcell.ftl" value="%{#status.index}" />
        <s:select name="termsId" list="genericList" listKey="id" listValue="name" />
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
        <s:textfield name="test" size="1"/>
    </s:iterator>

    <s:submit value="Create Sales Order" align="center">
        <s:param name="colspan" value="%{8}" />
        <s:param name="align" value="%{'center'}" />
    </s:submit>
</s:form>



