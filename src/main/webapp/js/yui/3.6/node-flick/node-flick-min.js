/*
YUI 3.6.0pr2 (build 5316)
Copyright 2012 Yahoo! Inc. All rights reserved.
Licensed under the BSD License.
http://yuilibrary.com/license/
*/
YUI.add("node-flick",function(c){var r="host",i="parentNode",s="boundingBox",a="offsetHeight",j="offsetWidth",e="scrollHeight",k="scrollWidth",g="bounce",o="minDistance",f="minVelocity",l="bounceDistance",d="deceleration",m="step",n="duration",p="easing",b="flick",h=c.ClassNameManager.getClassName;function q(t){q.superclass.constructor.apply(this,arguments);}q.ATTRS={deceleration:{value:0.98},bounce:{value:0.7},bounceDistance:{value:150},minVelocity:{value:0},minDistance:{value:10},boundingBox:{valueFn:function(){return this.get(r).get(i);}},step:{value:10},duration:{value:null},easing:{value:null}};q.NAME="pluginFlick";q.NS="flick";c.extend(q,c.Plugin.Base,{initializer:function(t){this._node=this.get(r);this._renderClasses();this.setBounds();this._node.on(b,c.bind(this._onFlick,this),{minDistance:this.get(o),minVelocity:this.get(f)});},setBounds:function(){var x=this.get(s),w=this._node,y=x.get(a),v=x.get(j),u=w.get(e),t=w.get(k);if(u>y){this._maxY=u-y;this._minY=0;this._scrollY=true;}if(t>v){this._maxX=t-v;this._minX=0;this._scrollX=true;}this._x=this._y=0;w.set("top",this._y+"px");w.set("left",this._x+"px");},_renderClasses:function(){this.get(s).addClass(q.CLASS_NAMES.box);this._node.addClass(q.CLASS_NAMES.content);},_onFlick:function(t){this._v=t.flick.velocity;this._flick=true;this._flickAnim();},_flickAnim:function(){var C=this._y,D=this._x,t=this._maxY,w=this._minY,u=this._maxX,z=this._minX,A=this._v,v=this.get(m),B=this.get(d),E=this.get(g);this._v=(A*B);this._snapToEdge=false;if(this._scrollX){D=D-(A*v);}if(this._scrollY){C=C-(A*v);}if(Math.abs(A).toFixed(4)<=q.VELOCITY_THRESHOLD){this._flick=false;this._killTimer(!(this._exceededYBoundary||this._exceededXBoundary));if(this._scrollX){if(D<z){this._snapToEdge=true;this._setX(z);}else{if(D>u){this._snapToEdge=true;this._setX(u);}}}if(this._scrollY){if(C<w){this._snapToEdge=true;this._setY(w);}else{if(C>t){this._snapToEdge=true;this._setY(t);}}}}else{if(this._scrollX&&(D<z||D>u)){this._exceededXBoundary=true;this._v*=E;}if(this._scrollY&&(C<w||C>t)){this._exceededYBoundary=true;this._v*=E;}if(this._scrollX){this._setX(D);}if(this._scrollY){this._setY(C);}this._flickTimer=c.later(v,this,this._flickAnim);}},_setX:function(t){this._move(t,null,this.get(n),this.get(p));},_setY:function(t){this._move(null,t,this.get(n),this.get(p));},_move:function(t,w,u,v){if(t!==null){t=this._bounce(t);}else{t=this._x;}if(w!==null){w=this._bounce(w);}else{w=this._y;}u=u||this._snapToEdge?q.SNAP_DURATION:0;v=v||this._snapToEdge?q.SNAP_EASING:q.EASING;this._x=t;this._y=w;this._anim(t,w,u,v);},_anim:function(t,B,v,A){var u=t*-1,z=B*-1,w={duration:v/1000,easing:A};if(c.Transition.useNative){w.transform="translate("+(u)+"px,"+(z)+"px)";}else{w.left=u+"px";w.top=z+"px";}this._node.transition(w);},_bounce:function(x,t){var v=this.get(g),w=this.get(l),u=v?-w:0;t=v?t+w:t;if(!v){if(x<u){x=u;}else{if(x>t){x=t;}}}return x;},_killTimer:function(){if(this._flickTimer){this._flickTimer.cancel();}}},{VELOCITY_THRESHOLD:0.015,SNAP_DURATION:400,EASING:"cubic-bezier(0, 0.1, 0, 1.0)",SNAP_EASING:"ease-out",CLASS_NAMES:{box:h(q.NS),content:h(q.NS,"content")}});c.Plugin.Flick=q;},"3.6.0pr2",{requires:["classnamemanager","transition","event-flick","plugin"]});