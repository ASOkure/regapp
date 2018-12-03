<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="/jsp/page/page_head.jsp"/>
<jsp:include page="/jsp/page/page_nav.jsp"/>

<br/>

<div style="float:left;">There are currently</div>
<div id="totalRecordNumber" class="total_number_div">
  <s:property value="totalRecordNumber"/>
</div>
<!-- get total number by JSON calls
<div id="totalRecordNumber" class="total_number_div"></div>
-->
<div style="float:left;"> records stored in the registry.</div>
<br/><br/>

<div id="json"></div>
<br/>
<br/>
<script type="text/javascript">
  YAHOO.util.Event.addListener(window, "load", function () {
    YAHOO.example.XHR_JSON = function () {
      this.parseNumber = function (sString) {
        sString = sString == null ? "" : sString;
        return YAHOO.util.DataSource.parseNumber(sString);//parseFloat(sString.substring(1));
      };
      // Custom function to sort by Column2 then by Column1
      var mySortFunction = function (a, b, desc) {
        // Deal with empty values
        if (!YAHOO.lang.isValue(a)) {
          return (!YAHOO.lang.isValue(b)) ? 0 : 1;
        }
        else if (!YAHOO.lang.isValue(b)) {
          return -1;
        }
        // First compare by Column2
        var comp = YAHOO.util.Sort.compare;
        //alert(a.getData("total"));
        var compState = comp(a.getData("total"), b.getData("total"), desc);
        // If values are equal, then compare by Column1
        return (compState !== 0) ? compState : comp(a.getData("european"), b.getData("european"), desc);
      };
      var columnWidth = 130;
      var columnMinWidth = 80;
      var myColumnDefs = [
        {key: "centre", label: "Centre", resizeable: true, sortable: true, width: 90, minWidth: 60},
        {key: "country", label: "Country", resizeable: true, sortable: true, width: 100, minWidth: columnMinWidth},
        {key: "local", label: "Data for local institution access Only", parser: 'number', resizeable: true, sortable: true, width: columnWidth, minWidth: columnMinWidth},
        {key: "national", label: "Data For national access Only", parser: this.parseNumber, resizeable: true, sortable: true, width: columnWidth, minWidth: columnMinWidth},
        {key: "european", label: "Data for EEA access only", parser: this.parseNumber, resizeable: true, sortable: true, width: columnWidth, minWidth: columnMinWidth},
        {key: "international", label: "Data for access by all international collaborators", parser: this.parseNumber, resizeable: true, sortable: true, width: columnWidth, minWidth: columnMinWidth},
        {key: "total", label: "Total", parser: this.parseNumber, resizeable: true, sortable: true, sortOptions: {sortFunction: mySortFunction}, width: columnWidth, minWidth: columnMinWidth}
        //,key:"sample", label:"Data &amp; Biomaterials Collected in EuroDSD Programme", parser:this.parseNumber, resizeable:true, sortable:true, width: columnWidth, minWidth: columnMinWidth}
      ];

      var myDataSource = new YAHOO.util.DataSource("/idsd/");

      myDataSource.responseType = YAHOO.util.DataSource.TYPE_JSON;
      myDataSource.connXhrMode = "queueRequests";
      myDataSource.responseSchema = {
        resultsList: "resultBean.resultList",
        fields: ["centre", "country", "local", "national", "european", "international", "total", "sample"]
        //metaFields : { totalRecords: 'total' }
      };
      //alert(totalRecords);

      var myDataTable = new YAHOO.widget.DataTable("json", myColumnDefs, myDataSource,
          //, {sortedBy:{key:"total", dir:YAHOO.widget.DataTable.CLASS_ASC}}
          {initialRequest: "record_statistic.action"}
      );

      myDataTable.subscribe("rowMouseoverEvent", myDataTable.onEventHighlightRow);
      myDataTable.subscribe("rowMouseoutEvent", myDataTable.onEventUnhighlightRow);

      var callbacks = {
        // Successful XHR response handler
        success: function (o) {
          // Use the JSON Utility to parse the data returned from the server
          try {
            var total = YAHOO.lang.JSON.parse(o.responseText).totalNumber;
            var element = YAHOO.util.Dom.get('totalRecordNumber');
            //alert(total);
            element.innerHTML = total;//must be innerHTML, not innerText (will not work in firfox)
          }
          catch (x) {
            //alert("JSON Parse failed!");
            return;
          }
        },
        failure: function (o) {
          if (!YAHOO.util.Connect.isCallInProgress(o)) {
            //alert("Data fetch failed. Please refresh the page if the contribution table is not properly populated. ");
          }
        },

        timeout: 100000
      };

      // Make the call to the server for JSON data
      YAHOO.util.Connect.asyncRequest('GET', "/idsd/total_number.action", callbacks);

      return {
        oDS: myDataSource,
        oDT: myDataTable
      };
    }();
  });
</script>

<jsp:include page="/jsp/page/page_foot.jsp"/>