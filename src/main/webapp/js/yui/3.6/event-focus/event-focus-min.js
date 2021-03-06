/*
YUI 3.6.0pr2 (build 5316)
Copyright 2012 Yahoo! Inc. All rights reserved.
Licensed under the BSD License.
http://yuilibrary.com/license/
 */
YUI
		.add(
				"event-focus",
				function(f) {
					var d = f.Event, c = f.Lang, a = c.isString, e = f.Array.indexOf, b = c
							.isFunction(f.DOM
									.create('<p onbeforeactivate=";"/>').onbeforeactivate);
					function g(i, h, k) {
						var j = "_" + i + "Notifiers";
						f.Event
								.define(
										i,
										{
											_attach : function(m, n, l) {
												if (f.DOM.isWindow(m)) {
													return d._attach([ i,
															function(o) {
																n.fire(o);
															}, m ]);
												} else {
													return d._attach([ h,
															this._proxy, m,
															this, n, l ], {
														capture : true
													});
												}
											},
											_proxy : function(o, s, q) {
												var p = o.target, m = o.currentTarget, r = p
														.getData(j), t = f
														.stamp(m._node), l = (b || p !== m), n;
												s.currentTarget = (q) ? p : m;
												s.container = (q) ? m : null;
												if (!r) {
													r = {};
													p.setData(j, r);
													if (l) {
														n = d._attach([ k,
																this._notify,
																p._node ]).sub;
														n.once = true;
													}
												} else {
													l = true;
												}
												if (!r[t]) {
													r[t] = [];
												}
												r[t].push(s);
												if (!l) {
													this._notify(o);
												}
											},
											_notify : function(w, q) {
												var C = w.currentTarget, l = C
														.getData(j), x = C
														.ancestors(), B = C
														.get("ownerDocument"), s = [], m = l ? f.Object
														.keys(l).length
														: 0, A, r, t, n, o, y, u, v, p, z;
												C.clearData(j);
												x.push(C);
												if (B) {
													x.unshift(B);
												}
												x._nodes.reverse();
												if (m) {
													y = m;
													x
															.some(function(H) {
																var G = f
																		.stamp(H), E = l[G], F, D;
																if (E) {
																	m--;
																	for (
																			F = 0,
																			D = E.length; F < D; ++F) {
																		if (E[F].handle.sub.filter) {
																			s
																					.push(E[F]);
																		}
																	}
																}
																return !m;
															});
													m = y;
												}
												while (m && (A = x.shift())) {
													n = f.stamp(A);
													r = l[n];
													if (r) {
														for (u = 0,
																v = r.length; u < v; ++u) {
															t = r[u];
															p = t.handle.sub;
															o = true;
															w.currentTarget = A;
															if (p.filter) {
																o = p.filter
																		.apply(
																				A,
																				[
																						A,
																						w ]
																						.concat(p.args
																								|| []));
																s
																		.splice(
																				e(
																						s,
																						t),
																				1);
															}
															if (o) {
																w.container = t.container;
																z = t.fire(w);
															}
															if (z === false
																	|| w.stopped === 2) {
																break;
															}
														}
														delete r[n];
														m--;
													}
													if (w.stopped !== 2) {
														for (u = 0,
																v = s.length; u < v; ++u) {
															t = s[u];
															p = t.handle.sub;
															if (p.filter
																	.apply(
																			A,
																			[
																					A,
																					w ]
																					.concat(p.args
																							|| []))) {
																w.container = t.container;
																w.currentTarget = A;
																z = t.fire(w);
															}
															if (z === false
																	|| w.stopped === 2) {
																break;
															}
														}
													}
													if (w.stopped) {
														break;
													}
												}
											},
											on : function(n, l, m) {
												l.handle = this._attach(
														n._node, m);
											},
											detach : function(m, l) {
												l.handle.detach();
											},
											delegate : function(o, m, n, l) {
												if (a(l)) {
													m.filter = function(p) {
														return f.Selector
																.test(
																		p._node,
																		l,
																		o === p ? null
																				: o._node);
													};
												}
												m.handle = this._attach(
														o._node, n, true);
											},
											detachDelegate : function(m, l) {
												l.handle.detach();
											}
										}, true);
					}
					if (b) {
						g("focus", "beforeactivate", "focusin");
						g("blur", "beforedeactivate", "focusout");
					} else {
						g("focus", "focus", "focus");
						g("blur", "blur", "blur");
					}
				}, "3.6.0pr2", {
					requires : [ "event-synthetic" ]
				});