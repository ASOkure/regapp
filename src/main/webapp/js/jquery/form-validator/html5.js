/**
 *  JQUERY-FORM-VALIDATOR
 *
 *  @version 2.2.141
 *  @website http://formvalidator.net/
 *  @author Victor Jonsson, http://victorjonsson.se
 *  @license MIT
 */
!function (a, b) {
  "use strict";
  var c = "placeholder" in document.createElement("INPUT"), d = "options" in document.createElement("DATALIST"), e = !1, f = function (b) {
    b.each(function () {
      var b = a(this), f = b.find("input,textarea,select"), g = !1;
      f.each(function () {
        var b = [], c = a(this), f = c.attr("required"), h = {};
        switch ((c.attr("type") || "").toLowerCase()) {
          case"time":
            b.push("time"), a.formUtils.validators.validate_date || e || (e = !0, a.formUtils.loadModules("date"));
            break;
          case"url":
            b.push("url");
            break;
          case"email":
            b.push("email");
            break;
          case"date":
            b.push("date");
            break;
          case"number":
            b.push("number");
            var i = c.attr("max"), j = c.attr("min");
            (j || i) && (j || (j = "0"), i || (i = "9007199254740992"), h["data-validation-allowing"] = "range[" + j + ";" + i + "]", (0 === j.indexOf("-") || 0 === i.indexOf("-")) && (h["data-validation-allowing"] += ",negative"), (j.indexOf(".") > -1 || i.indexOf(".") > -1) && (h["data-validation-allowing"] += ",float"))
        }
        if (c.attr("pattern") && (b.push("custom"), h["data-validation-regexp"] = c.attr("pattern")), c.attr("maxlength") && (b.push("length"), h["data-validation-length"] = "max" + c.attr("maxlength")), !d && c.attr("list")) {
          var k = [], l = a("#" + c.attr("list"));
          if (l.find("option").each(function () {
                k.push(a(this).text())
              }), 0 === k.length) {
            var m = a.trim(a("#" + c.attr("list")).text()).split("\n");
            a.each(m, function (b, c) {
              k.push(a.trim(c))
            })
          }
          l.remove(), a.formUtils.suggest(c, k)
        }
        f && 0 === b.length && b.push("required"), b.length && (f || (h["data-validation-optional"] = "true"), g = !0, c.attr("data-validation", b.join(" ")), a.each(h, function (a, b) {
          c.attr(a, b)
        }))
      }), g && b.trigger("html5ValidationAttrsFound"), c || f.filter("input[placeholder]").each(function () {
        this.defaultValue = this.getAttribute("placeholder"), a(this).bind("focus", function () {
          this.value === this.defaultValue && (this.value = "", a(this).removeClass("showing-placeholder"))
        }).bind("blur", function () {
          "" === a.trim(this.value) && (this.value = this.defaultValue, a(this).addClass("showing-placeholder"))
        })
      })
    })
  };
  a(b).bind("validatorsLoaded formValidationSetup", function (b, c) {
    c || (c = a("form")), f(c)
  }), a.formUtils.setupValidationUsingHTML5Attr = f
}(jQuery, window);