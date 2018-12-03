/*
YUI 3.6.0pr2 (build 5316)
Copyright 2012 Yahoo! Inc. All rights reserved.
Licensed under the BSD License.
http://yuilibrary.com/license/
*/
YUI.add("slider-base",function(c){var b=c.Attribute.INVALID_VALUE;function a(){a.superclass.constructor.apply(this,arguments);}c.SliderBase=c.extend(a,c.Widget,{initializer:function(){this.axis=this.get("axis");this._key={dim:(this.axis==="y")?"height":"width",minEdge:(this.axis==="y")?"top":"left",maxEdge:(this.axis==="y")?"bottom":"right",xyIndex:(this.axis==="y")?1:0};this.publish("thumbMove",{defaultFn:this._defThumbMoveFn,queuable:true});},renderUI:function(){var d=this.get("contentBox");this.rail=this.renderRail();this._uiSetRailLength(this.get("length"));this.thumb=this.renderThumb();this.rail.appendChild(this.thumb);d.appendChild(this.rail);d.addClass(this.getClassName(this.axis));},renderRail:function(){var e=this.getClassName("rail","cap",this._key.minEdge),d=this.getClassName("rail","cap",this._key.maxEdge);return c.Node.create(c.substitute(this.RAIL_TEMPLATE,{railClass:this.getClassName("rail"),railMinCapClass:e,railMaxCapClass:d}));},_uiSetRailLength:function(d){this.rail.setStyle(this._key.dim,d);},renderThumb:function(){this._initThumbUrl();var d=this.get("thumbUrl");return c.Node.create(c.substitute(this.THUMB_TEMPLATE,{thumbClass:this.getClassName("thumb"),thumbShadowClass:this.getClassName("thumb","shadow"),thumbImageClass:this.getClassName("thumb","image"),thumbShadowUrl:d,thumbImageUrl:d,thumbAriaLabelId:this.getClassName("label",c.guid())}));},_onThumbClick:function(d){this.thumb.focus();},bindUI:function(){var d=this.get("boundingBox"),f=(!c.UA.opera)?"down:":"press:",e=f+"38,40,33,34,35,36",h=f+"37,39",g=f+"37+meta,39+meta";d.on("key",this._onDirectionKey,e,this);d.on("key",this._onLeftRightKey,h,this);d.on("key",this._onLeftRightKeyMeta,g,this);this.thumb.on("click",this._onThumbClick,this);this._bindThumbDD();this._bindValueLogic();this.after("disabledChange",this._afterDisabledChange);this.after("lengthChange",this._afterLengthChange);},_incrMinor:function(){this.set("value",(this.get("value")+this.get("minorStep")));},_decrMinor:function(){this.set("value",(this.get("value")-this.get("minorStep")));},_incrMajor:function(){this.set("value",(this.get("value")+this.get("majorStep")));},_decrMajor:function(){this.set("value",(this.get("value")-this.get("majorStep")));},_setToMin:function(d){this.set("value",this.get("min"));},_setToMax:function(d){this.set("value",this.get("max"));},_onDirectionKey:function(d){d.preventDefault();if(this.get("disabled")===false){switch(d.charCode){case 38:this._incrMinor();break;case 40:this._decrMinor();break;case 36:this._setToMin();break;case 35:this._setToMax();break;case 33:this._incrMajor();break;case 34:this._decrMajor();break;}}},_onLeftRightKey:function(d){d.preventDefault();if(this.get("disabled")===false){switch(d.charCode){case 37:this._decrMinor();break;case 39:this._incrMinor();break;}}},_onLeftRightKeyMeta:function(d){d.preventDefault();if(this.get("disabled")===false){switch(d.charCode){case 37:this._setToMin();break;case 39:this._setToMax();break;}}},_bindThumbDD:function(){var d={constrain:this.rail};d["stick"+this.axis.toUpperCase()]=true;this._dd=new c.DD.Drag({node:this.thumb,bubble:false,on:{"drag:start":c.bind(this._onDragStart,this)},after:{"drag:drag":c.bind(this._afterDrag,this),"drag:end":c.bind(this._afterDragEnd,this)}});this._dd.plug(c.Plugin.DDConstrained,d);},_bindValueLogic:function(){},_uiMoveThumb:function(e,d){if(this.thumb){this.thumb.setStyle(this._key.minEdge,e+"px");d||(d={});d.offset=e;this.fire("thumbMove",d);}},_onDragStart:function(d){this.fire("slideStart",{ddEvent:d,originEvent:d});},_afterDrag:function(f){var g=f.info.xy[this._key.xyIndex],d=f.target.con._regionCache[this._key.minEdge];this.fire("thumbMove",{offset:(g-d),ddEvent:f,originEvent:f});},_afterDragEnd:function(d){this.fire("slideEnd",{ddEvent:d,originEvent:d});},_afterDisabledChange:function(d){this._dd.set("lock",d.newVal);},_afterLengthChange:function(d){if(this.get("rendered")){this._uiSetRailLength(d.newVal);this.syncUI();}},syncUI:function(){this._dd.con.resetCache();this._syncThumbPosition();this.thumb.set("aria-valuemin",this.get("min"));this.thumb.set("aria-valuemax",this.get("max"));this._dd.set("lock",this.get("disabled"));},_syncThumbPosition:function(){},_setAxis:function(d){d=(d+"").toLowerCase();return(d==="x"||d==="y")?d:b;},_setLength:function(e){e=(e+"").toLowerCase();var f=parseFloat(e,10),d=e.replace(/[\d\.\-]/g,"")||this.DEF_UNIT;return f>0?(f+d):b;},_initThumbUrl:function(){if(!this.get("thumbUrl")){var e=this.getSkinName()||"sam",d=c.config.base;if(d.indexOf("http://yui.yahooapis.com/combo")===0){d="http://yui.yahooapis.com/"+c.version+"/build/";}this.set("thumbUrl",d+"slider-base/assets/skins/"+e+"/thumb-"+this.axis+".png");}},BOUNDING_TEMPLATE:"<span></span>",CONTENT_TEMPLATE:"<span></span>",RAIL_TEMPLATE:'<span class="{railClass}">'+'<span class="{railMinCapClass}"></span>'+'<span class="{railMaxCapClass}"></span>'+"</span>",THUMB_TEMPLATE:'<span class="{thumbClass}" aria-labelledby="{thumbAriaLabelId}" aria-valuetext="" aria-valuemax="" aria-valuemin="" aria-valuenow="" role="slider" tabindex="0">'+'<img src="{thumbShadowUrl}" '+'alt="Slider thumb shadow" '+'class="{thumbShadowClass}">'+'<img src="{thumbImageUrl}" '+'alt="Slider thumb" '+'class="{thumbImageClass}">'+"</span>"},{NAME:"sliderBase",ATTRS:{axis:{value:"x",writeOnce:true,setter:"_setAxis",lazyAdd:false},length:{value:"150px",setter:"_setLength"},thumbUrl:{value:null,validator:c.Lang.isString}}});},"3.6.0pr2",{requires:["widget","substitute","dd-constrain","event-key"]});