/*
YUI 3.6.0pr2 (build 5316)
Copyright 2012 Yahoo! Inc. All rights reserved.
Licensed under the BSD License.
http://yuilibrary.com/license/
*/
YUI.add("pjax",function(c){var b="error",a="load";c.Pjax=c.Base.create("pjax",c.Router,[c.PjaxBase],{initializer:function(){this.publish(b,{defaultFn:this._defCompleteFn});this.publish(a,{defaultFn:this._defCompleteFn});},getContent:function(g){var f={},d=this.get("contentSelector"),i=c.Node.create(g||""),e=this.get("titleSelector"),h;if(d){f.node=c.one(i.all(d).toFrag());}else{f.node=i;}if(e){h=i.one(e);if(h){f.title=h.get("text");}}return f;},_defaultRoute:function(e){var d=e.url;this._request&&this._request.abort();if(this.get("addPjaxParam")){d=d.replace(/([^#]*)(#.*)?$/,function(f,h,g){h+=(h.indexOf("?")>-1?"&":"?")+"pjax=1";return h+(g||"");});}this._request=c.io(d,{arguments:{url:d},context:this,headers:{"X-PJAX":"true"},timeout:this.get("timeout"),on:{end:this._onPjaxIOEnd,failure:this._onPjaxIOFailure,success:this._onPjaxIOSuccess}});},_defCompleteFn:function(g){var d=this.get("container"),f=g.content;if(d&&f.node){d.setContent(f.node);}if(f.title&&c.config.doc){c.config.doc.title=f.title;}},_onPjaxIOEnd:function(){this._request=null;},_onPjaxIOFailure:function(g,e,d){var f=this.getContent(e.responseText);this.fire(b,{content:f,responseText:e.responseText,status:e.status,url:d.url});},_onPjaxIOSuccess:function(g,e,d){var f=this.getContent(e.responseText);this.fire(a,{content:f,responseText:e.responseText,status:e.status,url:d.url});}},{ATTRS:{addPjaxParam:{value:true},container:{value:null,setter:function(d){return d?c.one(d):null;}},contentSelector:{value:null},routes:{value:[{path:"*",callback:"_defaultRoute"}]},titleSelector:{value:"title"},timeout:{value:30000}}});},"3.6.0pr2",{requires:["pjax-base","io-base"]});