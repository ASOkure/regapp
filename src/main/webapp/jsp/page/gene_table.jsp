<%@ taglib prefix="s" uri="/struts-tags" %>
<div id="gene_list" class="div_hidden">
  <div class="hd">Make your selection</div>
  <div id="gene_list_bd" class="bd" style="overflow: auto;">
    <table class="query-page-table">
      <tr>
        <s:iterator value="geneCategories" >
        <s:if test="categoryId == 1" >
        <td class="query-page-table-td-left"></s:if >
          <s:if test="categoryId == 3" ></td>
        <td class="query-page-table-td-right"></s:if >
          <s:if test="categoryId == 2 || categoryId == 5" >
          <table class="gene-table-bottom"></s:if >
            <s:else >
            <table class="gene-table"></s:else >
              <tr>
                <td class="gene-table-checkbox-td">
                  <s:checkbox name="%{'gene_catetory_' + categoryId}" value="false" theme="simple" onclick="categorySelection(this);"/>
                </td>
                <td>
                  <s:div id="%{'category_' + categoryId}" onclick="categoryDIVSelection(this);">
                    <h3><s:property value="name"/></h3>
                  </s:div>
                </td>
              </tr>
              <s:iterator value="genes" id="g" status="gs">
                <s:if test="#gs.odd == true"><tr class="parameter-line-double" id="gene_table_tr_<s:property value="geneId"/>"></s:if>
                <s:else><tr class="parameter-line-single" id="gene_table_tr_<s:property value="geneId"/>"></s:else>
                <td class="gene-table-checkbox-td" id="gene_table_td_<s:property value="geneId"/>">
                  <s:checkbox id="%{'check_gene_' + geneId}" name="check_gene" theme="simple" cssClass="gene_checkbox"/>
                </td>
                <td onclick="makeSelection(this);">
                  <s:div id="%{'gene_' + geneId}" onclick="makeSelection(this);">
                    <s:div id="%{'gene_name_' + geneId}" cssClass="gene_name"><s:property value="geneName"/></s:div>
                    <s:div id="%{'gene_description_' + geneId}" cssClass="gene_description"> <s:property value="description"/></s:div>
                  </s:div>
                </td>
                </tr>
              </s:iterator>
            </table>
            </s:iterator >
            </td>
            </tr>
          </table>
  </div>
  <div class="ft"></div>
</div>