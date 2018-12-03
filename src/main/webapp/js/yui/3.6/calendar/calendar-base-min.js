/*
YUI 3.6.0pr2 (build 5316)
Copyright 2012 Yahoo! Inc. All rights reserved.
Licensed under the BSD License.
http://yuilibrary.com/license/
*/
YUI.add("calendar-base",function(b){var c=b.ClassNameManager.getClassName,n="calendar",d=c(n,"grid"),l=c(n,"body"),h=c(n,"header"),i=c(n,"header-wrap"),o=c(n,"weekdayrow"),q=c(n,"weekday"),e=c(n,"row"),a=c(n,"day"),p=c(n,"anchor"),k=b.Lang,f=b.Node.create,g=b.substitute,m=b.each;function j(){j.superclass.constructor.apply(this,arguments);}b.CalendarBase=b.extend(j,b.Widget,{initializer:function(){},renderUI:function(){var r=this.get("contentBox");r.appendChild(this._initCalendarHTML(new Date("5/21/1947")));},bindUI:function(){},_getCutoffColumn:function(r){var s=new Date(r.getFullYear(),r.getMonth(),1);return(1-s.getDay());},_initCalendarHTML:function(s){var r=s||new Date(),y=this._getCutoffColumn(r),C={calheader:(r.getMonth()+1)+"/"+r.getFullYear()},z="",B={wday1:"Su",wday2:"Mo",wday3:"Tu",wday4:"We",wday5:"Th",wday6:"Fr",wday7:"Sa"};var w={};w["header_template"]=g(j.HEADER_TEMPLATE,C);w["weekday_row"]="";m(B,function(D){w["weekday_row"]+=g(j.WEEKDAY_TEMPLATE,{weekdayname:D});});w["wdayrow_template"]=g(j.WEEKDAY_ROW_TEMPLATE,w);var x=[];for(var u=0;u<=5;u++){var A="";for(var t=-5;t<=7;t++){A+=g(j.CALDAY_TEMPLATE,{day_content:""+(t+7*u),day_display_status:(t>=y&&t<=(y+6))?"":'style="display:none;"'});}x.push(g(j.CALDAY_ROW_TEMPLATE,{calday_row:A}));}w["body_template"]=x.join("\n");var v=g(g(j.CONTENT_TEMPLATE,w),j.CALENDAR_CLASSES);return v;}},{CALENDAR_CLASSES:{calendar_grid_class:d,calendar_body_class:l,calendar_hd_class:h,calendar_hd_wrapper_class:i,calendar_weekdayrow_class:o,calendar_weekday_class:q,calendar_row_class:e,calendar_day_class:a,calendar_dayanchor_class:p},CONTENT_TEMPLATE:'<table class="{calendar_grid_class}">'+"<thead>"+"{header_template}"+"{wdayrow_template}"+"</thead>"+'<tbody class="{calendar_body_class}">'+"{body_template}"+"</tbody>"+"</table>",HEADER_TEMPLATE:"<tr>"+'<th colspan="7" class="{calendar_hd_class}">'+'<div id="calheader" class="{calendar_hd_wrapper_class}">'+"{calheader}"+"</div>"+"</th>"+"</tr>",WEEKDAY_ROW_TEMPLATE:'<tr class="{calendar_weekdayrow_class}">'+"{weekday_row}"+"</tr>",CALDAY_ROW_TEMPLATE:'<tr class="{calendar_row_class}">'+"{calday_row}"+"</tr>",WEEKDAY_TEMPLATE:'<th class="{calendar_weekday_class}">{weekdayname}</th>',CALDAY_TEMPLATE:'<td class="{calendar_day_class}" {day_display_status}>'+'<a href="#" class="{calendar_dayanchor_class}">'+"{day_content}"+"</a>"+"</td>",NAME:"calendarBase",ATTRS:{}});},"3.6.0pr2",{requires:["widget","datatype-date"]});