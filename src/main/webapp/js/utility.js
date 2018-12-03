//centre leaders
var leaderMap = new Array();

function search_init() {
  checkbox_init();
  leader_fill_init();
  ems_init("firstDsdAssessment");
  ems_init("latestDsdAssessment");
  gene_search_init();
}

function search_my_centre_init() {
  checkbox_init();
  ems_init("firstDsdAssessment");
  ems_init("latestDsdAssessment");
  gene_search_init();
}

function create_core_init() {
  leader_fill_init();
  consent_warning_init();
}

function create_diagnosis_init() {
  sample_share_history_init_radio();
  dsd_history_init_radio();
  infertility_history_init_radio();
}

function create_assessment_init() {
  datePicker_init();
  ems_init("dsdAssessment");
}

function create_gene_analysis_init() {
  //register the listeners for select boxes in the page.
  geneModuleInit_multi();
  //init previous selections of screened genes
  gene_screened_table_init();
  //init the gene selection window.
  gene_window_init();
}

function create_cah_init() {
  datePicker_init();
}

function congenitalAbnormalitiesInit() {
  YUI().use("dump", "node", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id;
    var congenitalAbnormalities = Y.one("#radio_param_1405_Yes");

    var onChangeHandle = function (e) {
      //alert(country.get('selectedIndex'));
      if (congenitalAbnormalities.get('checked') == true) {
        YAHOO.namespace("eurodsd.container");
        // Define various event handlers for Dialog
        var handleOK = function () {
          this.hide();
        };

        // Instantiate the Dialog
        YAHOO.eurodsd.container.infertilityHistoryDialog = new YAHOO.widget.SimpleDialog("infertility_history_dialog",
            {
              width: "400px",
              fixedcenter: true,
              visible: false,
              draggable: true,
              close: true,
              modal: true,
              icon: YAHOO.widget.SimpleDialog.ICON_TIP,
              constraintoviewport: true,
              buttons: [{text: "OK", handler: handleOK, isDefault: true}]
            });

        YAHOO.eurodsd.container.infertilityHistoryDialog.setHeader("Please visit other secitons to fill in more details");
        YAHOO.eurodsd.container.infertilityHistoryDialog.setBody("Please visit other secitons to fill in more details");

        // Render the Dialog
        YAHOO.eurodsd.container.infertilityHistoryDialog.render("dialog_box_div");
        YAHOO.eurodsd.container.infertilityHistoryDialog.show();
      }
    };
    congenitalAbnormalities.on("change", onChangeHandle);
  });
}

function create_cah_visit_init() {
  datePicker_init_all();
  freeTextBoxInit();
  bmi_bsa_calculation();
  episodes_init(0);
  glucocorticoid_init(0);
  treatmentChangeInit();
  congenitalAbnormalitiesInit();
}

function view_cah_visit_init() {
  episodes_init(1);
  glucocorticoid_init(1);
}

function edit_core_init() {
  leader_fill_init();
}

function edit_diagnosis_init() {
  sample_share_history_init_radio();
  dsd_history_init_radio();
  infertility_history_init_radio();
}

/**
 * Init function for edit_view_3.jsp
 * @return
 */
function edit_gene_analysis_init() {
  //register the listeners for select boxes in the page.
  geneModuleInit_multi();
  //init previous selections of screened genes
  gene_screened_table_init();
  //init the gene selection window.
  gene_window_init();
}

function replaceEpisodeId(node, oldNum, newNum) {
  if (node != null && node.getDOMNode().nodeType == 1) {
    node.set("id", node.get("id").replace("_" + oldNum + "_", "_" + newNum + "_"));
  }
}

function episodes_init(isReadOnly) {
  YUI().use("dump", "node", "event-valuechange", "panel", "dd-plugin", "datatype-number", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id + "_";
    var tbody = Y.one('#episode_panel_tbody');
    var episodeTr0 = Y.one('#episode_tr_0');
    Y.one("#" + formName + "dsdCahVisit_sickDayEpisodes").on("valuechange", function (e) {
      var newNumber = e.currentTarget.get("value");
      if (newNumber > 0) {
        newNumber = Y.DataType.Number.parse(newNumber);
        //init
        var previousNumber = tbody.all('tr').size();
        // Y.log("previousNumber = " + previousNumber);
        // Y.log("newNumber = " + newNumber);
        if (newNumber > previousNumber) {
          // clone from Node 0
          for (var i = previousNumber + 1; i <= newNumber; i++) {
            var episodeTrClone = episodeTr0.cloneNode(true);
            episodeTrClone.set("id", "episode_tr_" + i);
            episodeTrClone.get("childNodes").each(function (childNode, j) {
              if (childNode.getDOMNode().nodeType == 1) {
                replaceEpisodeId(childNode, 1, i);
                if (childNode.get("id").endsWith("_1")) {
                  childNode.empty();
                  childNode.append(i);
                } else {
                  childNode.get("childNodes").each(function (inputNode, k) {
                    if (inputNode.getDOMNode().nodeType == 1) {
                      replaceEpisodeId(inputNode, 1, i);
                      //update name
                      inputNode.set("name", inputNode.get("name").replace("[0]", "[" + (i - 1) + "]"));
                      inputNode.set("value", "");
                    }
                  });
                }
              }
            });
            tbody.appendChild(episodeTrClone);
            //re-activate validator
            Validation.autoBind();
          }
        } else if (newNumber < previousNumber) {
          for (var j = (newNumber + 1); j <= previousNumber; j++) {
            Y.one('#episode_tr_' + j).remove(true);
          }
        }
        episodeDialog.show();
        Y.one('#episode_0_days').focus();
      }
    });

    var episodeDialog = new Y.Panel({
      srcNode: '#episodePanelContent',
//            headerContent: 'Enter more details about each episode',
//            bodyContent: '<div class="message icon-warn">Are you sure you want to [take some action]?</div>',
      width: 1200,
      centered: true,
      modal: true, // modal behavior
      render: true,
      visible: false // make visible explicitly with .show()
    });

    //register buttons
    if (!isReadOnly) {
      episodeDialog.addButton({
        value: 'Cancel',
        section: Y.WidgetStdMod.FOOTER,
        action: function (e) {
          e.preventDefault();
          this.hide();
          // the callback is not executed, and is
          // callback reference removed, so it won't persist
          this.callback = false;
          Y.one("#" + formName + "dsdCahVisit_sickDayEpisodes").focus();
        }
      });
      episodeDialog.addButton({
        value: 'Save',
        section: Y.WidgetStdMod.FOOTER,
        action: function (e) {
          e.preventDefault();
          //write results to hidden fields

          this.hide();
          // code that executes the user confirmed action goes here
          if (this.callback) {
            this.callback();
          }
          // callback reference removed, so it won't persist
          this.callback = false;

          // put sum to "Number of sick days in total"
          var totalDaysField = Y.one("#" + formName + "dsdCahVisit_sickDayTotal");
          var total = 0;
          Y.one('#episode_panel_tbody').all(".number-days-textfield").each(function (inputNode, i) {
            total += Y.DataType.Number.parse(inputNode.get("value"));
          });
          totalDaysField.set("value", total);

          //write to hidden fields for save
          var div = Y.one('#hidden_episodes');
          //<input type="hidden" name="registerId" value="1593" id="upload_cah_visits_registerId"/>
          //clean previous selection
          div.empty();
          //add new ones
          var episodeNumberString = "";
          Y.one('#episode_panel_tbody').get("childNodes").each(function (trNode, i) {
            if (trNode.getDOMNode().nodeType == 1) {
              trNode.get("childNodes").each(function (tdNode, j) {
                if (tdNode.getDOMNode().nodeType == 1) {
                  var id = tdNode.get("id").strip();
                  if (id.endsWith("_2")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("id").endsWith('_days')) {
                        episodeNumberString += "[" + inputNode.get("value") + "|";
                      }
                    });
                  } else if (id.endsWith("_3")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("id").endsWith('_oral_steroids')) {
                        episodeNumberString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (id.endsWith("_4")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("id").endsWith('_hc_injection')) {
                        episodeNumberString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (id.endsWith("_5")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("id").endsWith('_adrenal_crisis')) {
                        episodeNumberString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (id.endsWith("_6")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("id").endsWith('_emergency')) {
                        episodeNumberString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (id.endsWith("_7")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("id").endsWith('_predisposing_condition')) {
                        episodeNumberString += inputNode.get("value") + "];";
                      }
                    });
                  }
                  // log(episodeNumberString);
                }
              });
            }
          });
          div.append("<input type='hidden' name='episodesString' value='" + episodeNumberString + "' id='episode_numbers_hidden_input'/>");
        }
      });
    }
    //line to view previous selections or amend
    Y.one('#view_episodes_link').on('click', function (e) {
      episodeDialog.show();
    });
  });
}

function log(text) {
  YUI().use("dump", "node", "event-valuechange", "panel", "dd-plugin", function (Y) {
    Y.log(text);
  });
}

function removeHiddenFromClassName(node) {
  if (null != node) {
    var className = node.get("className");
    if (className.contains("-hidden")) {
      node.set("className", className.replace("-hidden", ""));
    }
  }
}

function addHiddenFromClassName(node) {
  if (null != node) {
    var className = node.get("className");
    if (!className.contains("-hidden")) {
      node.set("className", className + "-hidden");
    }
  }
}

function glucocorticoid_init(isReadOnly) {
  var tableName = "cah_visit_master_table";
  YUI().use("node", "panel", "dd-plugin", "datatype-number", function (Y) {
    var inputYes = Y.one('#radio_param_1304_Yes');
    var inputNo = Y.one('#radio_param_1304_No');
    var inputNotKnown = Y.one('[id="radio_param_1304_Not Known"]');
    var formName = document.getElementsByTagName("form")[0].id + "_";
    //var inputDetail = Y.one('#' + formName + 'dsdCahVisit_glucocorticoidDetail');

    //var detailTrNode = Y.one('#tr_note_1304');
    var noteTrNode = Y.one('#tr_note_1305');

    if (inputYes != null && inputYes.get("checked")) {
      //removeHiddenFromClassName(detailTrNode);
      removeHiddenFromClassName(noteTrNode);
    }

    var showTrNodes = function () {
      //removeHiddenFromClassName(detailTrNode);
      removeHiddenFromClassName(noteTrNode);
      showMedDetailDialog();
    }

    var showMedDetailDialog = function (e) {
      //var medSelected = Y.one('#med_selected');
      //medSelected.empty();
      //medSelected.append(e.currentTarget.get('value'));

      medDialog.plug(Y.Plugin.Drag, {
        handles: [
          '.yui3-widget-hd'
        ]
      });
      //show the med pop box
      medDialog.show();
    }

    var medDialog = new Y.Panel({
      srcNode: '#medPanelContent',
      //width        : 1000,
      centered: true,
      modal: true, // modal behavior
      render: true,
      visible: false, // make visible explicitly with .show()
      render: true,
      plugins: [Y.Plugin.Drag]
    });
    if (!isReadOnly) {
      medDialog.addButton({
        value: 'Cancel',
        section: Y.WidgetStdMod.FOOTER,
        action: function (e) {
          e.preventDefault();
          this.hide();
          // the callback is not executed, and is
          // callback reference removed, so it won't persist
          this.callback = false;
          Y.one("#" + formName + "dsdCahVisit_glucocorticoidNote").focus();
        }
      });

      medDialog.addButton({
        value: 'Add Row',
        section: Y.WidgetStdMod.FOOTER,
        action: function (e) {
          e.preventDefault();
          // the callback is not executed, and is
          // callback reference removed, so it won't persist
          this.callback = false;
          //clone a new row
          //find the count
          var tbody = Y.one("#med_panel_tbody");
          var existingRowCount = Y.DataType.Number.parse(tbody.all('tr').size());
          var nextRowId = existingRowCount + 1;
          //find first row
          var firstMedTr = Y.one("#med_tr_0");
          var newMedTrRow = firstMedTr.cloneNode(true);
          //update ids on the new row
          newMedTrRow.set("id", "med_tr_" + nextRowId);
          newMedTrRow.get("childNodes").each(function (childNode, j) {
            if (childNode.getDOMNode().nodeType == 1) {
              replaceEpisodeId(childNode, 1, nextRowId);
              if (childNode.get("id").endsWith("_1")) {
                childNode.empty();
                childNode.append(nextRowId);
              } else {
                childNode.get("childNodes").each(function (inputNode, k) {
                  if (inputNode.getDOMNode().nodeType == 1) {
                    replaceEpisodeId(inputNode, 0, nextRowId);
                    //update name
                    inputNode.set("name", inputNode.get("name").replace("[0]", "[" + (nextRowId - 1) + "]"));
                    inputNode.set("value", "");
                  }
                });
              }
            }
          });
          tbody.appendChild(newMedTrRow);
          //re-activate validator
          Validation.autoBind();
        }
      });

      medDialog.addButton({
        value: 'Save',
        section: Y.WidgetStdMod.FOOTER,
        action: function (e) {
          e.preventDefault();
          //write results to hidden fields

          this.hide();
          // code that executes the user confirmed action goes here
          if (this.callback) {
            this.callback();
          }
          // callback reference removed, so it won't persist
          this.callback = false;

          // write to hidden fields for saving
          var div = Y.one('#hidden_med');
          div.empty();
          var medDetailString = "";
          Y.one("#med_panel_tbody").get("childNodes").each(function (trNode, i) {
            if (trNode.getDOMNode().nodeType == 1) {
              trNode.get("childNodes").each(function (tdNode, j) {
                if (tdNode.getDOMNode().nodeType == 1) {
                  if (tdNode.get("id").endsWith("_2")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("tagName") == 'SELECT' && inputNode.get("id").endsWith('_medicine')) {
                        medDetailString += "[" + inputNode.get("value") + "|";
                      }
                    });
                  } else if (tdNode.get("id").endsWith("_3")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("tagName") == 'INPUT' && inputNode.get("id").endsWith('_dose')) {
                        medDetailString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (tdNode.get("id").endsWith("_4")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("tagName") == 'SELECT' && inputNode.get("id").endsWith('_unit')) {
                        medDetailString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (tdNode.get("id").endsWith("_5")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("tagName") == 'INPUT' && inputNode.get("id").endsWith('_time')) {
                        medDetailString += inputNode.get("value") + "|";
                      }
                    });
                  } else if (tdNode.get("id").endsWith("_6")) {
                    tdNode.get("childNodes").each(function (inputNode, m) {
                      if (inputNode.getDOMNode().nodeType == 1 && inputNode.get("tagName") == 'TEXTAREA' && inputNode.get("id").endsWith('_note')) {
                        var note = inputNode.get("value");
                        var escapedNote = escape(note);
                        // Y.log("escapedNote = " + escapedNote);
                        medDetailString += escapedNote + "];";
                      }
                    });
                  }
                }
              });
            }
          });
          // log("med string = " + medDetailString);
          div.append("<input type='hidden' name='medicineDetailString' value='" + medDetailString + "' id='med_detail_hidden_input'/>");
        }
      });
    }

    var hideTrNodes = function () {
      //addHiddenFromClassName(detailTrNode);
      addHiddenFromClassName(noteTrNode);
    }

    if (inputYes != null) {
      inputYes.on("change", showTrNodes);
    }
    if (inputNo != null) {
      inputNo.on("change", hideTrNodes);
    }
    if (inputNotKnown != null) {
      inputNotKnown.on("change", hideTrNodes);
    }
    //inputDetail.on("change", showMedDetailDialog);

    //can view the previous selection
    Y.one('#view_med_link').on('click', function (e) {
      medDialog.show();
    });
  });
}


function bmi_bsa_calculation() {
  YUI().use("dump", "node", "event-valuechange", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id + "_";

    var bmiBsaCalc = function (e) {
      var weight = Y.one("#" + formName + "dsdCahVisit_weight").get("value");
      var height = Y.one("#" + formName + "dsdCahVisit_height").get("value");
      var bmiField = Y.one("#" + formName + "dsdCahVisit_bmi");
      var bsaField = Y.one("#" + formName + "dsdCahVisit_bsa");
      if (weight != null && height != null) {
        var bmi = calcBMI(weight, height);
        if (bmi > 0) {
          bmiField.set("value", bmi);
        }
        var bsa = calcBSA(weight, height, "Mosteller");
        if (bsa > 0) {
          bsaField.set("value", bsa);
        }
      }
    };

    var weightField = Y.one("#" + formName + "dsdCahVisit_weight");
    var heightField = Y.one("#" + formName + "dsdCahVisit_height");

    weightField.on("valuechange", bmiBsaCalc);
    heightField.on("valuechange", bmiBsaCalc);
  });
}

function calcBMI(weight, height) {
  var bmi = 0;
  if (weight > 0 && height > 0) {
    bmi = weight / ( height / 100 * height / 100 )
  }
  return bmi.toFixed(2);
  ;
}

/**
 /// <summary>returns the body surface area in meters^2; calling with a single arg is assumed to be 'wt'</summary>
 /// <param name="ht">height, units = cm</param>
 /// <param name="wt">weight, units = kg</param>
 /// <param name="BSAMethod">optional; type = string (any of the following:"DuBois", "Haycock"(default), "Gehan", "Mosteller", "Boyd", or "Dreyer")</param>
 /// <returns>Number</returns>
 */
function calcBSA(wt, ht, BSAMethod) {
  var bsa;
  // allow for ht and method to be optional:
  if (!ht && !BSAMethod) {
    // only one arg was passed, 'wt'
    var BSAMethod = "Dreyer";
  } else if (!BSAMethod) {
    var BSAMethod = "Haycock";
  }
  switch (BSAMethod) {
    case "DuBois":
      bsa = 0.007184 * Math.pow(ht, 0.725) * Math.pow(wt, 0.425);
      break;
    case "Haycock":
      bsa = 0.024265 * Math.pow(ht, 0.3964) * Math.pow(wt, 0.5378);
      break;
    case "Gehan":
      bsa = 0.0235 * Math.pow(ht, 0.42246) * Math.pow(wt, 0.51456);
      break;
    case "Mosteller":
      bsa = Math.sqrt((ht * wt) / 3600);
      break;
    case "Boyd":
      wt = wt * 1000;
      var exponent = 0.7285 - 0.0188 * (Math.LOG10E * Math.log(wt)); //necessary to get the Log base 10 of (wt)
      bsa = 0.0003207 * Math.pow(ht, 0.3) * Math.pow(wt, exponent);
      break;
    case "Dreyer":
      bsa = 0.1 * Math.pow(wt, (2 / 3));
      break;
    default:
      bsa = 0.024265 * Math.pow(ht, 0.3964) * Math.pow(wt, 0.5378);// returns Haycock in the event an unfamiliar method is passed in
      break;
  } //end switch
  return bsa.toFixed(2);
}

function convertFieldNameToId(fieldName) {
  var id;
  var formName = document.getElementsByTagName("form")[0].id + "_";
  if (null != fieldName) {
    id = fieldName.replace(".", "_");
  }
  return formName + id;
}

function freeTextBoxInit() {
  var tableName = "cah_visit_master_table";
  YUI().use("event", "node", function (Y) {
    var table = Y.one('#' + tableName);
    table.all("input").each(function (input, i) {
      if (input.get('type') == 'radio' && !input.get('id').startsWith('radio_param_1304')) {
        var textAreaId = convertFieldNameToId(input.get("name")) + "Note";
        var textArea = Y.one("#" + textAreaId);
        if (null != textArea) {
          var noteTr = textArea.get("parentNode").get("parentNode");
          //init the selected values on edit
          if (input.get("checked") && ('Yes' === input.get('value') || 'Current' === input.get('value') || 'Ex-smoker' === input.get('value'))) {
            removeHiddenFromClassName(noteTr);
          }
          //register event handlers
          if ('Yes' === input.get('value') || 'Current' === input.get('value') || 'Ex-smoker' === input.get('value')) {
            //show
            input.on("change", function () {
              removeHiddenFromClassName(noteTr);
            });
          }
          if ('No' === input.get('value') || 'Not Known' === input.get('value') || 'Never' === input.get('value')) {
            //hide
            input.on("change", function () {
              addHiddenFromClassName(noteTr);
            });
          }
        }
      }
    });
  });
}

function findGrandparentNode(treatmentChangeReasonNote) {
  return treatmentChangeReasonNote.get("parentNode").get("parentNode");
}

function treatmentChangeInit() {
  var formName = document.getElementsByTagName("form")[0].id + "_";
  YUI().use("event", "node", function (Y) {
    var treatmentChange = Y.one('#' + formName + 'dsdCahVisit_treatmentChange');
    var treatmentChangeReason = Y.one('#' + formName + 'dsdCahVisit_treatmentChangeReason'); //upload_cah_visits_dsdCahVisit_treatmentChangeReason
    var treatmentChangeReasonNote = Y.one('#' + formName + 'dsdCahVisit_treatmentChangeReasonNote'); //upload_cah_visits_dsdCahVisit_treatmentChangeReasonNote

    initialize_treatment_reason(treatmentChange, treatmentChangeReason);

    initialize_treatment_note_field(treatmentChangeReason, treatmentChangeReasonNote);

    treatmentChange.on("change", function () {
      treatmentChangeReason.set("selectedIndex", 6);
    });

    treatmentChangeReason.on("change", function (e) {
      var noteTr = findGrandparentNode(treatmentChangeReasonNote);
      if (e.currentTarget.get('value') === 'Other') {
        removeHiddenFromClassName(noteTr);
      } else {
        addHiddenFromClassName(noteTr);
      }
    });
  });

  function initialize_treatment_reason(treatmentChange, treatmentChangeReason) {
    var treatmentChangeValue = treatmentChange.get("value");
    var treatmentChangeReasonValue = treatmentChangeReason.get("value");
    if (treatmentChangeValue === 'No' && treatmentChangeReasonValue === "") {
      treatmentChangeReason.set("selectedIndex", 6);
    }
  }

  function initialize_treatment_note_field(treatmentChangeReason, treatmentChangeReasonNote) {
    var noteTr = findGrandparentNode(treatmentChangeReasonNote);
    if (treatmentChangeReason.get('value') === 'Other') {
      removeHiddenFromClassName(noteTr);
    }
  }
}

function gene_search_init() {
  YUI().use("event", "node", function (Y) {
    var prefix = document.getElementsByTagName("form")[0].id + "_";
    var singleGeneAnalysis = Y.one("#" + prefix + "dsdGeneAnalysis_singleGeneAnalysis");
    var selectedGene = Y.one("#selected_gene");
    var geneListDiv = Y.one("#gene_list");
    //init window
    YAHOO.namespace("eurodsd.container");

    var search_gene_handle = function (e) {
      var checkboxs = geneListDiv.all(".gene_checkbox");
      var geneString = "";
      checkboxs.each(function (checkbox, i) {
        if (checkbox.get("checked")) {
          var id = checkbox.get("id").substring(checkbox.get("id").lastIndexOf('_') + 1);
          //var gene_name = $("gene_name_" + id).textContent.strip();
          var gene_name = geneListDiv.one("#gene_name_" + id).get("innerHTML").strip();
          geneString += gene_name + ", ";
        }
      });
      //trim string and get rid of the ending comma
      geneString = geneString.strip();
      if (geneString.endsWith(",")) {
        geneString = geneString.substring(0, geneString.length - 1);
      }
      selectedGene.set("value", geneString);
      YAHOO.eurodsd.container.geneList.hide();
    };
    var height = "560px";
    if (screen.height <= 600) {
      height = "400px";
    }
    Y.one("#gene_list").removeClass("div_hidden");
    //width: "645px", height: "560px"
    YAHOO.eurodsd.container.geneList = new YAHOO.widget.Dialog("gene_list",
        {
          width: "645px",
          height: height,
          fixedcenter: true,
          visible: false,
          modal: true,
          constraintoviewport: true,
          buttons: [{text: "Submit", handler: search_gene_handle, isDefault: true},
            {text: "Cancel", handler: gene_cancel}]
        });
    // Render the Dialog
    YAHOO.eurodsd.container.geneList.render();

    var gene_selection_init = function (e) {
      var selectedGeneStr = selectedGene.get("value");
      if (selectedGeneStr != null && selectedGeneStr != "") {
        var genes = selectedGeneStr.split(',');
        var geneDivs = geneListDiv.all(".gene_name");
        geneDivs.each(function (geneDiv, i) {
          genes.each(function (geneName, j) {
            if (geneDiv.get("innerHTML").strip() == geneName.strip()) {
              var geneId = geneDiv.get("id").substring(geneDiv.get("id").lastIndexOf("_") + 1);
              Y.one("#check_gene_" + geneId).set("checked", true);
            }
          });
        });

      }
    };
    //select box onchange handler
    var onChangeHandle = function (e) {
      if (singleGeneAnalysis.get("selectedIndex") == 1) {
        gene_selection_init();
        YAHOO.eurodsd.container.geneList.show();
      } else {
        YAHOO.eurodsd.container.geneList.hide();
      }
    };
    singleGeneAnalysis.on("change", onChangeHandle);

    var onEditHandle = function (e) {
      gene_selection_init();
      YAHOO.eurodsd.container.geneList.show();
    }
    Y.one("#edit_gene_selection").on("click", onEditHandle);
  });
}


function sample_share_history_init_radio() {
  YUI().use("dump", "node", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id;
    var sampleShared = Y.one("#" + formName + "_dsdIdentifier_sampleSharedYes");
    var onChangeHandle = function (e) {
      //alert(sampleShared.get('selected') );
      if (sampleShared.get('checked') == true) {
        YAHOO.namespace("eurodsd.container");
        // Define various event handlers for Dialog
        var handleOK = function () {
          this.hide();
        };

        // Instantiate the Dialog
        YAHOO.eurodsd.container.sampleSharedDialog = new YAHOO.widget.SimpleDialog("sample_shared_dialog",
            {
              width: "400px",
              fixedcenter: true,
              visible: false,
              draggable: true,
              close: true,
              modal: true,
              //text: "Please ensure that your standards of data entry comply with the <a  target='_blank' ref='https://tethys.nesc.gla.ac.uk/EuroDSD/docs/sop/The%20ESPE%20DSD%20Registry%20SOP160609.pdf'>standard operating protocol</a>.",
              icon: YAHOO.widget.SimpleDialog.ICON_TIP,
              constraintoviewport: true,
              buttons: [{text: "OK", handler: handleOK, isDefault: true}]
            });

        YAHOO.eurodsd.container.sampleSharedDialog.setHeader("Please enter sample sharing history");
        YAHOO.eurodsd.container.sampleSharedDialog.setBody(
            "You have selected yes to <i>Sample Share</i>." +
            "Please give more details in the <i>Free Text</i> box about the sample you have shared, " +
            "who you shared with, and when. ");
        // Render the Dialog
        YAHOO.eurodsd.container.sampleSharedDialog.render("dialog_box_div");
        YAHOO.eurodsd.container.sampleSharedDialog.show();
      }
    };
    sampleShared.on("change", onChangeHandle);
  });
}

function dsd_history_init_radio() {
  YUI().use("dump", "node", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id;
    var dsdHistory = Y.one("#" + formName + "_dsdIdentifier_dsdHistoryYes");
    var onChangeHandle = function (e) {
      if (dsdHistory.get('checked') == true) {
        YAHOO.namespace("eurodsd.container");
        // Define various event handlers for Dialog
        var handleOK = function () {
          this.hide();
        };

        // Instantiate the Dialog
        YAHOO.eurodsd.container.dsdHistoryDialog = new YAHOO.widget.SimpleDialog("dsd_history_dialog",
            {
              width: "400px",
              fixedcenter: true,
              visible: false,
              draggable: true,
              close: true,
              modal: true,
              icon: YAHOO.widget.SimpleDialog.ICON_TIP,
              constraintoviewport: true,
              buttons: [{text: "OK", handler: handleOK, isDefault: true}]
            });

        YAHOO.eurodsd.container.dsdHistoryDialog.setHeader("Please add details of DSD history");
        YAHOO.eurodsd.container.dsdHistoryDialog.setBody("Please provide further details of family history in the <i>Free Text</i> box.");

        // Render the Dialog
        YAHOO.eurodsd.container.dsdHistoryDialog.render("dialog_box_div");
        YAHOO.eurodsd.container.dsdHistoryDialog.show();
      }
    };
    dsdHistory.on("change", onChangeHandle);
  });
}

function infertility_history_init_radio() {
  YUI().use("dump", "node", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id;
    var infertilityHistory = Y.one("#" + formName + "_dsdIdentifier_infertilityHistoryYes");

    var onChangeHandle = function (e) {
      if (infertilityHistory.get('checked') == true) {
        YAHOO.namespace("eurodsd.container");
        // Define various event handlers for Dialog
        var handleOK = function () {
          this.hide();
        };

        // Instantiate the Dialog
        YAHOO.eurodsd.container.infertilityHistoryDialog = new YAHOO.widget.SimpleDialog("infertility_history_dialog",
            {
              width: "400px",
              fixedcenter: true,
              visible: false,
              draggable: true,
              close: true,
              modal: true,
              icon: YAHOO.widget.SimpleDialog.ICON_TIP,
              constraintoviewport: true,
              buttons: [{text: "OK", handler: handleOK, isDefault: true}]
            });

        YAHOO.eurodsd.container.infertilityHistoryDialog.setHeader("Please add details of infertility history");
        YAHOO.eurodsd.container.infertilityHistoryDialog.setBody("Please provide further details of infertility history in the <i>Free Text</i> box.");

        // Render the Dialog
        YAHOO.eurodsd.container.infertilityHistoryDialog.render("dialog_box_div");
        YAHOO.eurodsd.container.infertilityHistoryDialog.show();
      }
    };
    infertilityHistory.on("change", onChangeHandle);
  });
}


function consent_warning_init() {
  YAHOO.namespace("eurodsd.container");
  // Define various event handlers for Dialog
  var handleOK = function () {
    this.hide();
  };

  // Instantiate the Dialog
  YAHOO.eurodsd.container.simpledialog1 = new YAHOO.widget.SimpleDialog("consent_warning_dialog",
      {
        width: "400px",
        fixedcenter: true,
        visible: false,
        draggable: true,
        close: true,
        modal: true,
        //text: "Please ensure that your standards of data entry comply with the <a  target='_blank' ref='https://tethys.nesc.gla.ac.uk/EuroDSD/docs/sop/The%20ESPE%20DSD%20Registry%20SOP160609.pdf'>standard operating protocol</a>.",
        icon: YAHOO.widget.SimpleDialog.ICON_WARN,
        constraintoviewport: true,
        buttons: [{text: "OK", handler: handleOK, isDefault: true}]
      });

  YAHOO.eurodsd.container.simpledialog1.setHeader("Consent confirmation before proceed!");
  YAHOO.eurodsd.container.simpledialog1.setBody("Please confirm that you have obtained patient/parental consent before proceeding.")
  // Render the Dialog
  YAHOO.eurodsd.container.simpledialog1.render("dialog_box_div");
  YAHOO.eurodsd.container.simpledialog1.show();
}


/**
 * Check box utility function for search page.
 */
function checkbox_init() {
  YUI().use("dump", "node", function (Y) {
    var checkAllBoxes = function (e) {
      if (this.get('checked')) {
        Y.all("#search_table input").each(function (input) {
          if (input.get('type') === 'checkbox' && (input.get('id').contains('parameter_checkbox_') || input.get('id').startsWith('section_check_')))
            input.set('checked', true);
        });
      } else {
        Y.all("#search_table input").each(function (input) {
          if (input.get('type') === 'checkbox' && (input.get('id').contains('parameter_checkbox_') || input.get('id').startsWith('section_check_')))
            input.set('checked', false);
        });
      }
    };
    var checkSectionBoxes = function (e) {
      var table = _recursion(this);
      if (this.get('checked')) {
        table.all("input").each(function (input, i) {
          if (input.get('type') === 'checkbox' && input.get('id').contains('parameter_checkbox_'))
            input.set('checked', true);
        });
      } else {
        table.all("input").each(function (input, i) {
          if (input.get('type') === 'checkbox' && input.get('id').contains('parameter_checkbox_'))
            input.set('checked', false);
        });
      }
    };
    var _recursion = function (o) {
      var parent = o.get('parentNode');
      if (parent.get('tagName').toLowerCase() === "TABLE".toLowerCase()) {
        return parent;
      } else {
        return _recursion(parent);
      }
    }
    Y.on("change", checkAllBoxes, "#check_all_box");
    Y.all('#search_table input').each(
        function (input) {
          if (input.get('type') === 'checkbox' && input.get('id').startsWith('section_check_'))
            input.on('click', checkSectionBoxes);
        }
    );
  });
}
/**
 * END OF SEARCH CHECKBOX FUNCTION
 */


/**
 * Auto fill centre leaders
 *
 */
function leader_fill_init() {
  YUI().use("dump", "node", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id;
    var country = Y.one("#" + formName + "_dsdIdentifier_countryName");
    var centre = Y.one("#" + formName + "_dsdIdentifier_centreName");
    var clinician = Y.one("#" + formName + "_dsdIdentifier_clinician");
    var contact = Y.one("#" + formName + "_dsdIdentifier_contact");

    var onChangeHandle = function () {
      if (country.get('selectedIndex') !== 0) {
        for (var i = 0; i < leaderMap.length; i++) {
          if (leaderMap[i][0].toLowerCase() === centre.get('value').toLowerCase()) {
            clinician.set('value', leaderMap[i][1]);
            contact.set('value', leaderMap[i][2]);
          }
        }
      } else {
        clinician.set('value', '');
        contact.set('value', '');
      }
    };
    country.on("change", onChangeHandle);
    centre.on("change", onChangeHandle);
  });
}
/**
 * END OF AUTO FILL FUNCTION
 */

function datePicker_init_all() {
  YUI().use('node', function (Y) {
    Y.all('.calendar_button').each(function (button, i) {
      var id = button.get("id").substring(button.get("id").lastIndexOf('_') + 1);
      datePicker_init(id);
    });
  });
}

function datePicker_init(id) {
  YAHOO.util.Event.onDOMReady(function () {
    var Event = YAHOO.util.Event,
        Dom = YAHOO.util.Dom;
    var calendarButtonId = "show_button" + (id == null ? "" : "_" + id);
    var showBtn = Dom.get(calendarButtonId);
    Event.on(showBtn, "click", function () {
      yui2_calendar_init(id);
    });
    var copyButtonId = "copy_button" + (id == null ? "" : "_" + id);
    var copyButton = Dom.get(copyButtonId);
    Event.on(copyButton, "click", function () {
      copyDateToOtherEmptyDateFields(id);
    });
  });
}
function copyDateToOtherEmptyDateFields(id) {
  YUI().use('node', function (Y) {
    var sourceDateField = Y.one('#date_field_' + id);
    var sourceDate = sourceDateField.get("value");
    if (sourceDate !== "") {
      var dateFields = Y.all('.calendar_text_field');
      for (var i = 0; i < dateFields.size(); i++) {
        var dateField = dateFields.item(i);
        var targetDate = dateField.get("value");
        if (targetDate === "") {
          dateField.set("value", sourceDate);
        }
      }
    }
  });
}

function yui2_calendar_init(id) {
  var Event = YAHOO.util.Event,
      Dom = YAHOO.util.Dom,
      dialog,
      calendar;
  var calendarButtonId = "show_button" + (id == null ? "" : "_" + id);
  var showBtn = Dom.get(calendarButtonId);
  // Lazy Dialog Creation - Wait to create the Dialog, and setup document click listeners, until the first time the button is clicked.
  if (!dialog) {
    // Hide Calendar if we click anywhere in the document other than the calendar
    Event.on(document, "click", function (e) {
      var el = Event.getTarget(e);
      var dialogEl = dialog.element;
      if (el != dialogEl && !Dom.isAncestor(dialogEl, el) && el != showBtn && !Dom.isAncestor(showBtn, el)) {
        dialog.hide();
      }
    });
    function resetHandler() {
      // Reset the current calendar page to the select date, or
      // to today if nothing is selected.
      var selDates = calendar.getSelectedDates();
      var resetDate;
      if (selDates.length > 0) {
        resetDate = selDates[0];
      } else {
        resetDate = calendar.today;
      }
      calendar.cfg.setProperty("pagedate", resetDate);
      calendar.render();
    }

    function closeHandler() {
      dialog.hide();
    }

    dialog = new YAHOO.widget.Dialog("container", {
      visible: false,
      context: [showBtn.id, "tl", "bl"],
      buttons: [{text: "Reset", handler: resetHandler, isDefault: true}, {text: "Close", handler: closeHandler}],
      draggable: false,
      close: true
    });
    dialog.setHeader('Pick A Date');
    dialog.setBody('<div id="cal"></div>');
    dialog.render(document.body);
    dialog.showEvent.subscribe(function () {
      if (YAHOO.env.ua.ie) {
        // Since we're hiding the table using yui-overlay-hidden, we
        // want to let the dialog know that the content size has changed, when shown
        dialog.fireEvent("changeContent");
      }
    });
  }
  // Lazy Calendar Creation - Wait to create the Calendar until the first time the button is clicked.
  if (!calendar) {
    calendar = new YAHOO.widget.Calendar("cal", {
      iframe: false,          // Turn iframe off, since container has iframe support.
      hide_blank_weeks: true  // Enable, to demonstrate how we handle changing height, using changeContent
    });
    calendar.cfg.setProperty("maxdate", calendar.today);
    calendar.render();
    calendar.selectEvent.subscribe(function () {
      var selectedDateFieldId = "date_field" + (id == null ? "" : "_" + id);
      var selectedDateField = Dom.get(selectedDateFieldId);
      if (calendar.getSelectedDates().length > 0) {
        var selDate = calendar.getSelectedDates()[0];
        selectedDateField.value = YAHOO.util.Date.format(selDate, {format: '%d/%m/%Y'});
      } else {
        selectedDateField.value = "";
      }
      dialog.hide();
    });
    calendar.renderEvent.subscribe(function () {
      // Tell Dialog it's contents have changed, which allows
      // container to redraw the underlay (for IE6/Safari2)
      dialog.fireEvent("changeContent");
    });
  }
  var seldate = calendar.getSelectedDates();
  if (seldate.length > 0) {
    // Set the pagedate to show the selected date if it exists
    calendar.cfg.setProperty("pagedate", seldate[0]);
    calendar.render();
  }
  dialog.show();
}

// used for User role assignment page
function user_datePicker_init(dateFieldName, buttonName) {
  YAHOO.util.Event.onDOMReady(function () {
    var Event = YAHOO.util.Event,
        Dom = YAHOO.util.Dom,
        dialog,
        calendar;
    var showBtn = Dom.get(buttonName);
    Event.on(showBtn, "click", function () {
      // Lazy Dialog Creation - Wait to create the Dialog, and setup document click listeners, until the first time the button is clicked.
      if (!dialog) {
        // Hide Calendar if we click anywhere in the document other than the calendar
        Event.on(document, "click", function (e) {
          var el = Event.getTarget(e);
          var dialogEl = dialog.element;
          if (el != dialogEl && !Dom.isAncestor(dialogEl, el) && el != showBtn && !Dom.isAncestor(showBtn, el)) {
            dialog.hide();
          }
        });
        function resetHandler() {
          // Reset the current calendar page to the select date, or
          // to today if nothing is selected.
          var selDates = calendar.getSelectedDates();
          var resetDate;
          if (selDates.length > 0) {
            resetDate = selDates[0];
          } else {
            resetDate = calendar.today;
          }
          calendar.cfg.setProperty("pagedate", resetDate);
          calendar.render();
        }

        function closeHandler() {
          dialog.hide();
        }

        dialog = new YAHOO.widget.Dialog("container", {
          visible: false,
          context: ["show", "tl", "bl"],
          buttons: [{text: "Reset", handler: resetHandler, isDefault: true}, {
            text: "Close",
            handler: closeHandler
          }],
          draggable: false,
          close: true
        });
        dialog.setHeader('Pick A Date');
        dialog.setBody('<div id="cal"></div>');
        dialog.render(document.body);
        dialog.showEvent.subscribe(function () {
          if (YAHOO.env.ua.ie) {
            // Since we're hiding the table using yui-overlay-hidden, we
            // want to let the dialog know that the content size has changed, when
            // shown
            dialog.fireEvent("changeContent");
          }
        });
      }
      // Lazy Calendar Creation - Wait to create the Calendar until the first time the button is clicked.
      if (!calendar) {
        calendar = new YAHOO.widget.Calendar("cal", {
          iframe: false,          // Turn iframe off, since container has iframe support.
          hide_blank_weeks: true  // Enable, to demonstrate how we handle changing height, using changeContent
        });
        calendar.render();
        calendar.selectEvent.subscribe(function () {
          if (calendar.getSelectedDates().length > 0) {
            var selDate = calendar.getSelectedDates()[0];
            Dom.get(dateFieldName).value = YAHOO.util.Date.format(selDate, {format: '%d/%m/%Y'});
          } else {
            Dom.get(dateFieldName).value = "";
          }
          dialog.hide();
        });
        calendar.renderEvent.subscribe(function () {
          // Tell Dialog it's contents have changed, which allows
          // container to redraw the underlay (for IE6/Safari2)
          dialog.fireEvent("changeContent");
        });
      }
      var seldate = calendar.getSelectedDates();
      if (seldate.length > 0) {
        // Set the pagedate to show the selected date if it exists
        calendar.cfg.setProperty("pagedate", seldate[0]);
        calendar.render();
      }
      dialog.show();
    });
  });
}

/**
 * Event listeners registered for gene module page.
 * @return
 */
function geneModuleInit_multi() {
  var prefix = document.getElementsByTagName("form")[0].id + "_dsdGeneAnalysis_";
  YUI().use("dump", "node", function (Y) {
    var geneticAnalysis = Y.one("#" + prefix + "geneticAnalysis");
    var geneSecondaryTable = Y.one("#gene_secondary_table");
    geneticAnalysis.on("change", function (e) {
      if (geneticAnalysis.get('selectedIndex') == 1) {
        if (geneSecondaryTable.get('className') == "secondary_table_hidden") {
          geneSecondaryTable.set("className", "secondary_table");
        }
      } else {
        if (geneSecondaryTable.get("className") == "secondary_table")
          geneSecondaryTable.set("className", "secondary_table_hidden");
      }
    });

    var chromosomalRearrangement = Y.one("#" + prefix + "chromosomalRearrangement");
    var chromosomalRearrangementDiv = Y.one("#chromosomal_rearrangement_detail");
    chromosomalRearrangement.on("change", function (e) {
      if (chromosomalRearrangement.get('selectedIndex') == 1) {
        if (chromosomalRearrangementDiv.get('className') == 'div_hidden')
          chromosomalRearrangementDiv.set('className', 'div_shown');
      } else {
        if (chromosomalRearrangementDiv.get('className') == 'div_shown')
          chromosomalRearrangementDiv.set('className', 'div_hidden');
      }
    });

    var cgh = Y.one("#" + prefix + "cgh");
    var cghDiv = Y.one("#cgh_detail");
    cgh.on("change", function (e) {
      if (cgh.get('selectedIndex') == 1) {
        if (cghDiv.get('className') == 'div_hidden')
          cghDiv.set('className', 'div_shown');
      } else {
        if (cghDiv.get('className') == 'div_shown')
          cghDiv.set('className', 'div_hidden');
      }
    });

    var functionalStudy = Y.one("#" + prefix + "functionalStudy");
    var functionalStudyDiv = Y.one("#functional_study_detail");
    functionalStudy.on("change", function (e) {
      if (functionalStudy.get('selectedIndex') == 1) {
        if (functionalStudyDiv.get('className') == 'div_hidden')
          functionalStudyDiv.set('className', 'div_shown');
      } else {
        if (functionalStudyDiv.get('className') == 'div_shown')
          functionalStudyDiv.set('className', 'div_hidden');
      }
    });

    var publishedCase = Y.one("#" + prefix + "publishedCase");
    var publishedCaseDiv = Y.one("#publish_detail");
    publishedCase.on("change", function (e) {
      if (publishedCase.get('selectedIndex') == 1) {
        if (publishedCaseDiv.get('className') == 'div_hidden')
          publishedCaseDiv.set('className', 'div_shown');
      } else {
        if (publishedCaseDiv.get('className') == 'div_shown')
          publishedCaseDiv.set('className', 'div_hidden');
      }
    });

  });
}
/**
 * END OF GENE MODULE PAGE EVENT LISTENER
 */

/**
 * GeneScreened class
 * @return
 */
function GeneScreened() {
  var geneId,
      geneName,
      geneDesc,
      sequenceMethod,
      otherMethod,
      mutationFound,
      mutationDetail;
}

/**
 * UPDATE FUNCTION: initialise DsdGeneScreened list for the update_view_3.jsp
 * @return
 */
function gene_screened_table_init() {
  var geneScreenedList = new Array();
  YUI().use("dump", "node", function (Y) {
    Y.all("#single_gene_init_value tbody tr").each(function (trNode, i) {
      var geneScreened = new GeneScreened();
      trNode.all("td").each(function (tdNode, i) {
        if (i == 0) geneScreened.geneId = tdNode.get("innerHTML").strip(); //use innerHTML or innerText both fine.
        if (i == 1) geneScreened.geneName = tdNode.get("innerHTML").strip();
        if (i == 2) geneScreened.geneDesc = tdNode.get("innerHTML").strip();
        if (i == 3) geneScreened.sequenceMethod = tdNode.get("innerHTML").strip();
        if (i == 4) geneScreened.otherMethod = tdNode.get("innerHTML").strip();
        if (i == 5) geneScreened.mutationFound = tdNode.get("innerHTML").strip();
        if (i == 6) geneScreened.mutationDetail = tdNode.get("innerHTML").strip();
      });
      geneScreenedList.push(geneScreened);
    });
    _gene_table_init_selection(geneScreenedList);
    //register onclick action for edit button
    Y.one("#screened_gene_edit").on("click", function (e) {
      YAHOO.eurodsd.container.geneList.show();
    });
  });
}

function _gene_table_init_selection(geneScreenedList) {
  //select genes in the gene pop up window
  YUI().use("dump", "node", function (Y) {
    geneScreenedList.each(function (geneScreened, i) {
      var checkbox = Y.one('#check_gene_' + geneScreened.geneId);
      checkbox.set('checked', true);
    });
    Y.all(".gene_sequence_select").each(function (sequenceMethodSelect, i) {
      var geneId = sequenceMethodSelect.get("id").substring(sequenceMethodSelect.get("id").lastIndexOf('_') + 1);

      var otherMethodDiv = Y.one("#sequence_text_" + geneId + "_div");
      var otherMethodTextarea = Y.one("#sequence_text_" + geneId);
      sequenceMethodSelect.on("change", function (e) {
        if (sequenceMethodSelect.get("selectedIndex") == 2 && otherMethodDiv.get("className") == "div_hidden") {
          otherMethodDiv.set("className", "div_shown");
        } else {
          otherMethodDiv.set("className", "div_hidden");
          otherMethodTextarea.set("value", "");
        }
      });
    });

    Y.all(".gene_mutation_checkbox").each(function (mutationCheckbox, i) {
      var geneId = mutationCheckbox.get("id").substring(mutationCheckbox.get("id").lastIndexOf('_') + 1);
      var mutationDetailDiv = Y.one("#mutation_detail_" + geneId + "_div");
      var mutationDetailTextarea = Y.one("#mutation_text_" + geneId);
      mutationCheckbox.on("change", function () {
        if (mutationCheckbox.get("checked") && mutationDetailDiv.get("className") == "div_hidden") {
          mutationDetailDiv.set("className", "div_shown");
        } else {
          mutationDetailDiv.set("className", "div_hidden");
          mutationDetailTextarea.set("value", "");
        }
      });

    });
  });
}

/**
 * give a list of GeneScreened, then construct a gene screen table based on the list
 * @param geneScreenedList
 * @return
 */
function _gene_table_generation_init(geneScreenedList) {
  YUI().use("dump", "node", function (Y) {
    //bug fix for safari 5+ and chrome 7 beta +
    Y.mix(Y.DOM.creators, {
      tbody: function (html, doc) {
        return Y.DOM.create('<table>' + html + '</table>', doc);
      },
      tr: function (html, doc) {
        return Y.DOM.create('<tbody>' + html + '</tbody>', doc);
      },
      td: function (html, doc) {
        return Y.DOM.create('<tr>' + html + '</tr>', doc);
      },
      option: function (html, doc) {
        return Y.DOM.create('<select><option class="yui3-big-dummy" selected></option>' + html + '</select>', doc);
      }
    });

    Y.mix(Y.DOM.creators, {
      legend: 'fieldset',
      th: Y.DOM.creators.td,
      thead: Y.DOM.creators.tbody,
      tfoot: Y.DOM.creators.tbody,
      caption: Y.DOM.creators.tbody,
      colgroup: Y.DOM.creators.tbody,
      col: Y.DOM.creators.tbody,
      optgroup: Y.DOM.creators.option
    });

    var table = Y.one("#single_analysis_table");
    var tbody = Y.one("#single_analysis_tbody");
    if ('' === tbody.get("innerHTML").strip()) {
      tbody = Y.Node.create("<tbody id='single_analysis_tbody'></tbody>");
      table.appendChild(tbody);

      if (table.get("className") === "tertiary_table_hidden" && geneScreenedList.size() > 0) {
        table.set("className", "tertiary_table");
      }

      var editButtonDiv = Y.one("#screened_gene_edit_button_div");
      if (editButtonDiv.get("className") === "screened_gene_edit_button_class_hidden" && geneScreenedList.size() > 0) {
        editButtonDiv.set("className", "screened_gene_edit_button_class");
      }

      geneScreenedList.each(function (geneScreened, i) {
        //make selection to the gene selection list that is hidden.
        var geneCheckbox = Y.one("#check_gene_" + geneScreened.geneId);
        geneCheckbox.set("checked", "true");

        gene_sequence_tr_builder(geneScreened, i);

      });
    } else {
      geneScreenedList.each(function (geneScreened, i) {
        //make selection to the gene selection list that is hidden.
        var geneCheckbox = Y.one("#check_gene_" + geneScreened.geneId);
        geneCheckbox.set("checked", "true");

        var geneSelected = Y.one("#gene_selection_tr_" + geneScreened.geneId);
        if (null == geneSelected) {
          gene_sequence_tr_builder(geneScreened, i);
        }
      });

      var previousSelectedGenes = new Array();
      tbody.all("tr").each(function (trNode) {
        var id = trNode.get("id").substring(trNode.get("id").lastIndexOf('_') + 1);
        previousSelectedGenes.push(id);
      });

      var currentSelectedGenes = new Array();
      geneScreenedList.each(function (geneScreened) {
        currentSelectedGenes.push(geneScreened.geneId);
      });

      previousSelectedGenes.each(function (geneId) {
        if (!currentSelectedGenes.contains(geneId)) {
          var toRemove = Y.one("#gene_selection_tr_" + geneId);
          tbody.removeChild(toRemove);
          //remove single gene init hidden table
          var toRemoveHidden = Y.one("#single_gene_init_tr_" + geneId);
          var singleTbody = Y.one("#single_gene_init_tbody");
          singleTbody.removeChild(toRemoveHidden);
        }
      });
    }

    YAHOO.eurodsd.container.geneList.hide();
  });
}

function gene_sequence_tr_builder(geneScreened, i) {
  YUI().use("dump", "node", function (Y) {
    var tbody = Y.one("#single_analysis_tbody");

    //generate gene screen table
    //FIRST TD
    var tdGene = Y.Node.create('<td id="gene_selection_gene_td_' + geneScreened.geneId + '" class="tertiary_table_td_desc"></td>');
    var geneIdDiv = Y.Node.create('<div id="sequence_gene_name_' + geneScreened.geneId + '" class="gene_name">' + geneScreened.geneName + '</div>');
    var geneDescDiv = Y.Node.create('<div id="sequence_gene_desc_' + geneScreened.geneId + '" class="gene_description">' + geneScreened.geneDesc + '</div>');
    var hiddenInput = Y.Node.create('<input type="hidden" id="sequence_gene_id_' + geneScreened.geneId + '" name="dsdGeneScreeneds[' + i + '].gene.geneId" value="' + geneScreened.geneId + '" />');
    tdGene.appendChild(geneIdDiv);
    tdGene.appendChild(geneDescDiv);
    tdGene.appendChild(hiddenInput);
    //END OF FIRST TD
    //SECOND TD
    var tdSequence = Y.Node.create('<td id="gene_selection_sequence_td_' + geneScreened.geneId + '"></td>');

    var sequenceMethodSelect = Y.Node.create('<select id="sequence_select_' + geneScreened.geneId + '" name="dsdGeneScreeneds[' + i + '].directSequence" class="gene_sequence_select">' +
        '<option value="">- Select -</option><option value="Yes">Yes</option><option value="No">No</option></select>');
    var otherMethodDiv = Y.Node.create('<div id="sequence_text_' + geneScreened.geneId + '_div" class="div_hidden"></div>');
    var otherMethod = Y.Node.create('<div>Other method: </div>');
    var otherMethodTextarea = Y.Node.create('<textarea id="sequence_text_' + geneScreened.geneId + '" rows="2" cols="20" name="dsdGeneScreeneds[' + i + '].otherSequence" class="gene_sequence_text"></textarea>');
    otherMethodDiv.appendChild(otherMethod);
    otherMethodDiv.appendChild(otherMethodTextarea);
    tdSequence.appendChild(sequenceMethodSelect);
    tdSequence.appendChild(otherMethodDiv);
    if (geneScreened.sequenceMethod == "Yes") {
      sequenceMethodSelect.set("selectedIndex", 1);
    } else if (geneScreened.sequenceMethod == "No") {
      sequenceMethodSelect.set("selectedIndex", 2);
      otherMethodDiv.set("className", "div_shown");
      otherMethodTextarea.set("value", geneScreened.otherMethod);
    }
    //END OF SECOND TD
    //3RD TD
    var tdMutation = Y.Node.create('<td id="gene_selection_mutation_td_' + geneScreened.geneId + '"></td>');
    var mutationCheckbox = Y.Node.create('<input type="checkbox" name="dsdGeneScreeneds[' + i + '].mutationFound" value="true" class="gene_mutation_checkbox">');
    var mutationDetailDiv = Y.Node.create('<div id="mutation_detail_' + geneScreened.geneId + '_div" class="div_hidden"></div>');
    var mutationDetail = Y.Node.create('<div>Mutation Details: </div>');
    var mutationDetailTextarea = Y.Node.create('<textarea id="mutation_text_' + geneScreened.geneId + '" rows="2" cols="20" name="dsdGeneScreeneds[' + i + '].mutationDetail" class="gene_mutation_text"></textarea>');

    mutationDetailDiv.appendChild(mutationDetail);
    mutationDetailDiv.appendChild(mutationDetailTextarea);
    tdMutation.appendChild(mutationCheckbox);
    tdMutation.appendChild(mutationDetailDiv);

    if (geneScreened.mutationFound === "true") {
      mutationCheckbox.set("checked", "true");
      mutationDetailDiv.set("className", "div_shown");
      mutationDetailTextarea.set("value", geneScreened.mutationDetail);
    }
    //END OF THIRD TD
    var tr = Y.Node.create('<tr id="gene_selection_tr_' + geneScreened.geneId + '"></tr>');
    tr.appendChild(tdGene);
    tr.appendChild(tdSequence);
    tr.appendChild(tdMutation);

    tbody.appendChild(tr);
    //register event listeners
    sequenceMethodSelect.on("change", function () {
      if (sequenceMethodSelect.get("selectedIndex") === 2 && otherMethodDiv.get("className") === "div_hidden") {
        otherMethodDiv.set("className", "div_shown");
      } else {
        otherMethodDiv.set("className", "div_hidden");
        otherMethodTextarea.set("value", "");
      }
    });
    mutationCheckbox.on("change", function () {
      if (mutationCheckbox.get("checked") && mutationDetailDiv.get("className") === "div_hidden") {
        mutationDetailDiv.set("className", "div_shown");
      } else {
        mutationDetailDiv.set("className", "div_hidden");
        mutationDetailTextarea.set("value", "");
      }
    });
  });
}


/**
 * on submit of gene window
 * @return
 */
function gene_selection_submit() {
  YUI().use("dump", "node", function (Y) {
    var geneScreenedList = new Array();
    Y.all("#gene_list_bd input").each(function (geneNode) {
      if (geneNode.get("type") === "checkbox" && geneNode.test(".gene_checkbox") && geneNode.get("checked")) {
        var geneScreened = new GeneScreened();
        geneScreened.geneId = geneNode.get("id").substring(11);
        geneScreened.geneName = geneNode.get("parentNode").get("parentNode").one("#gene_name_" + geneScreened.geneId).get("innerHTML").strip();
        geneScreened.geneDesc = geneNode.get("parentNode").get("parentNode").one("#gene_description_" + geneScreened.geneId).get("innerHTML").strip();
        geneScreenedList.push(geneScreened);
      }
    });
    _gene_table_generation_init(geneScreenedList);
  });
}

/**
 * on cancel of gene window
 * @return
 */
function gene_cancel() {
  YAHOO.eurodsd.container.geneList.hide();
}


/**
 * init of gene window.
 * @return
 */
function gene_window_init() {
  YAHOO.namespace("eurodsd.container");

  // Define various event handlers for Dialog
  var handleSuccess = function (o) {
    //var response = o.responseText;
    //response = response.split("<!")[0];
    //document.getElementById("resp").innerHTML = response;
  };

  var handleFailure = function (o) {
    alert("Submission failed: " + o.status);
  };

  var geneWindowHeight = "560px";
  if (screen.height <= 600) {
    geneWindowHeight = "400px";
  }
  // Instantiate the Dialog
  document.getElementById("gene_list").style.display = "block";
  YAHOO.eurodsd.container.geneList = new YAHOO.widget.Dialog("gene_list",
      {
        width: "645px",
        height: geneWindowHeight,
        fixedcenter: true,
        visible: false,
        modal: true,
        constraintoviewport: true,
        buttons: [{text: "Submit", handler: gene_selection_submit, isDefault: true},
          {text: "Cancel", handler: gene_cancel}]
      });

  // Validate the entries in the form to require that both first and last name are entered
  YAHOO.eurodsd.container.geneList.validate = function () {
    var data = this.getData();
    if (data.firstname == "" || data.lastname == "") {
      alert("Please enter your first and last names.");
      return false;
    } else {
      return true;
    }
  };

  // Wire up the success and failure handlers
  YAHOO.eurodsd.container.geneList.callback = {
    success: handleSuccess,
    failure: handleFailure
  };

  // Render the Dialog
  YAHOO.eurodsd.container.geneList.render();

  var prefix = document.getElementsByTagName("form")[0].id + "_dsdGeneAnalysis_";
  var singleGeneAnalysis = document.getElementById(prefix + "singleGeneAnalysis");
  //define event listener for select
  YAHOO.util.Event.addListener(singleGeneAnalysis, "change", singleGeneAnalysis_multi);
}

/*
 * single gene analysis module functions
 */
function singleGeneAnalysis_multi() {
  var prefix = document.getElementsByTagName("form")[0].id + "_dsdGeneAnalysis_";

  var singleGeneAnalysis = $(prefix + "singleGeneAnalysis");
  if (singleGeneAnalysis.selectedIndex === 1) {
    YAHOO.eurodsd.container.geneList.show();
  } else {
    var table = $("single_analysis_table");
    if (table.className === "tertiary_table")
      table.className = "tertiary_table_hidden";
    YAHOO.eurodsd.container.geneList.hide();
  }
}


/**
 * functions supports gene selection in the gene window
 * @param object
 * @return
 */
function makeSelection(object) {
  var number = object.id.substring(object.id.lastIndexOf('_') + 1);
  var checkbox = $("check_gene_" + number);
  if (checkbox != null) {
    if (checkbox.checked === true) {
      checkbox.checked = false;
    } else {
      checkbox.checked = true;
    }
  }
}

function categorySelection(checkbox) {
  var table = _recursion(checkbox);
  var array = table.getElementsByTagName("input");
  var cba = new Array();
  var counter = 0;
  for (var i = 0; i < array.length; i++) {
    if (array[i].type === 'checkbox' && array[i].id.indexOf("check_gene_") !== -1) {
      cba[counter] = array[i];
      counter++;
    }
  }
  if (checkbox.checked === true) {
    for (var j = 0; j < cba.length; j++) {
      cba[j].checked = true;
    }
  } else {
    for (var k = 0; k < cba.length; k++) {
      cba[k].checked = false;
    }
  }
}

function categoryDIVSelection(object) {
  var categoryId = object.id.substring(object.id.lastIndexOf("_") + 1);
  var checkbox = $('gene_catetory_' + categoryId);
  if (checkbox != null) {
    if (checkbox.checked === true) {
      checkbox.checked = false;
    } else {
      checkbox.checked = true;
    }
    categorySelection(checkbox);
  }
}

//find the neareat parent TABLE
function _recursion(obj) {
  var parent = obj.parentNode;
  if (parent.tagName.toLowerCase() === "TABLE".toLowerCase()) {
    return parent;
  } else {
    return _recursion(parent);
  }
}
/**
 * END OF GENE SELECTION UTILITY FUNCTIONS
 */


/**
 * EMS calculation
 */
function ems_init(param) {
  YUI().use("dump", "node", function (Y) {
    var formName = document.getElementsByTagName("form")[0].id + "_" + param + "_";
    var phallusSize, urinaryMeatus, labFusion, rightGonad, leftGonad;
    var ems;

    var emsInit = function () {
      var phallusSizeIndex = Y.one("#" + formName + "phallusSize").get('selectedIndex');
      var urinaryMeatusIndex = Y.one("#" + formName + "urinaryMeatus").get('selectedIndex');
      var labFusionIndex = Y.one("#" + formName + "labioscrotalFusion").get('selectedIndex');
      var rightGonadIndex = Y.one("#" + formName + "rightGonad").get('selectedIndex');
      var leftGonadIndex = Y.one("#" + formName + "leftGonad").get('selectedIndex');
      if (phallusSizeIndex >= 1) {
        phallusSize = parseFloat(Y.one("#" + formName + "phallusSize_" + (phallusSizeIndex - 1)).get('value'));
      }
      if (urinaryMeatusIndex >= 1) {
        urinaryMeatus = parseFloat(Y.one("#" + formName + "urinaryMeatus_" + (urinaryMeatusIndex - 1)).get("value"));
      }
      if (labFusionIndex >= 1) {
        labFusion = parseFloat(Y.one("#" + formName + "labioscrotalFusion_" + (labFusionIndex - 1)).get("value"));
      }
      if (rightGonadIndex >= 1) {
        rightGonad = parseFloat(Y.one("#" + formName + "rightGonad_" + (rightGonadIndex - 1)).get("value"));
      }
      if (leftGonadIndex >= 1) {
        leftGonad = parseFloat(Y.one("#" + formName + "leftGonad_" + (leftGonadIndex - 1)).get("value"));
      }
      //phallusSize + urinaryMeatus + labFusion + rightGonad + leftGonad;
      if (YAHOO.lang.isNumber(phallusSize) &&
          YAHOO.lang.isNumber(urinaryMeatus) &&
          YAHOO.lang.isNumber(labFusion) &&
          YAHOO.lang.isNumber(rightGonad) &&
          YAHOO.lang.isNumber(leftGonad)) {
        ems = phallusSize + urinaryMeatus + labFusion + rightGonad + leftGonad;
        Y.one("#" + formName + "ems").set('value', ems);
      }
    };

    emsInit();

    var emsCal = function (e) {
      var id = e.currentTarget.get('id').substring(e.currentTarget.get('id').lastIndexOf('_') + 1);
      switch (id) {
        case 'phallusSize' :
          phallusSize = parseFloat(Y.one("#" + formName + "phallusSize_" + (e.currentTarget.get('selectedIndex') - 1)).get("value"));
          break;
        case 'urinaryMeatus' :
          urinaryMeatus = parseFloat(Y.one("#" + formName + "urinaryMeatus_" + (e.currentTarget.get('selectedIndex') - 1)).get("value"));
          break;
        case 'labioscrotalFusion' :
          labFusion = parseFloat(Y.one("#" + formName + "labioscrotalFusion_" + (e.currentTarget.get('selectedIndex') - 1)).get("value"));
          break;
        case 'rightGonad' :
          rightGonad = parseFloat(Y.one("#" + formName + "rightGonad_" + (e.currentTarget.get('selectedIndex') - 1)).get("value"));
          break;
        case 'leftGonad' :
          leftGonad = parseFloat(Y.one("#" + formName + "leftGonad_" + (e.currentTarget.get('selectedIndex') - 1)).get("value"));
          break;
      }

      if (YAHOO.lang.isNumber(phallusSize) &&
          YAHOO.lang.isNumber(urinaryMeatus) &&
          YAHOO.lang.isNumber(labFusion) &&
          YAHOO.lang.isNumber(rightGonad) &&
          YAHOO.lang.isNumber(leftGonad)) {
        ems = phallusSize + urinaryMeatus + labFusion + rightGonad + leftGonad;
        Y.one("#" + formName + "ems").set('value', ems);
      }
    };
    Y.on("change", emsCal, "#" + formName + "phallusSize");
    Y.on("change", emsCal, "#" + formName + "urinaryMeatus");
    Y.on("change", emsCal, "#" + formName + "labioscrotalFusion");
    Y.on("change", emsCal, "#" + formName + "rightGonad");
    Y.on("change", emsCal, "#" + formName + "leftGonad");
  });
}


function result_access_select_all() {
  YUI().use("dump", "node", function (Y) {
    var checkAllNode = Y.one("#check_all_box");
    var onChangeHandle = function () {
      var checkboxes = Y.all("#research_result_upload_form input");
      if (checkAllNode.get("type") === "checkbox" && checkAllNode.get("checked")) {
        checkboxes.each(function (checkbox) {
          if (checkbox.get("type") === "checkbox" && !checkbox.get("checked")) {
            checkbox.set("checked", true);
          }
        });
      } else if (checkAllNode.get("type") === "checkbox" && !checkAllNode.get("checked")) {
        checkboxes.each(function (checkbox) {
          if (checkbox.get("type") === "checkbox" && checkbox.get("checked")) {
            checkbox.set("checked", false);
          }
        });
      }
    };
    checkAllNode.on("change", onChangeHandle);
  });
}

/**
 * String prototype method extension
 -- cause YUI3 menu to fail if missing str in function(str).
 */
String.prototype.trim = function (str) {
  return this.replace(/^[\s\xA0]+/, "").replace(/[\s\xA0]+$/, "");
};

String.prototype.startsWith = function (str) {
  return this.match("^" + str) == str;
};

String.prototype.endsWith = function (str) {
  return this.match(str + "$") == str;
};

String.prototype.contains = function (str) {
  return this.indexOf(str) >= 0;
};

Array.prototype.contains = function (obj) {
  var i = this.length;
  while (i--) {
    if (this[i] === obj) {
      return true;
    }
  }
  return false;
};
/**
 * END OF STRING CLASS EXTENSION
 */


/**
 * Common initializer for page_header.jsp to initialize validator and year range.
 */
function initial_validation(Y) {
  Y.on("domready", function () {
    //Change validation year range to current year
    var node = Y.all(".int-range-1900-9999");
    var newClassName = ".int-range-1900-" + (new Date()).getFullYear();
    node.removeClass('int-range-1900-9999');
    node.addClass(newClassName);
  });
}

/**
 * Tooltip initializer
 */
function initial_tooltip(Y) {
  var Lang = Y.Lang,
      Node = Y.Node,
      OX = -10000,
      OY = -10000;

  var Tooltip = Y.Base.create("tooltip", Y.Widget, [Y.WidgetPosition, Y.WidgetStack], {

    // PROTOTYPE METHODS/PROPERTIES

    /*
     * Initialization Code: Sets up privately used state
     * properties, and publishes the events Tooltip introduces
     */
    initializer: function (config) {

      this._triggerClassName = this.getClassName("trigger");

      // Currently bound trigger node information
      this._currTrigger = {
        node: null,
        title: null,
        mouseX: Tooltip.OFFSCREEN_X,
        mouseY: Tooltip.OFFSCREEN_Y
      };

      // Event handles - mouse over is set on the delegate
      // element, mousemove and mouseleave are set on the trigger node
      this._eventHandles = {
        delegate: null,
        trigger: {
          mouseMove: null,
          mouseOut: null
        }
      };

      // Show/hide timers
      this._timers = {
        show: null,
        hide: null
      };

      // Publish events introduced by Tooltip. Note the triggerEnter event is preventable,
      // with the default behavior defined in the _defTriggerEnterFn method
      this.publish("triggerEnter", {defaultFn: this._defTriggerEnterFn, preventable: true});
      this.publish("triggerLeave", {preventable: false});
    },

    /*
     * Destruction Code: Clears event handles, timers,
     * and current trigger information
     */
    destructor: function () {
      this._clearCurrentTrigger();
      this._clearTimers();
      this._clearHandles();
    },

    /*
     * bindUI is used to bind attribute change and dom event
     * listeners
     */
    bindUI: function () {
      this.after("delegateChange", this._afterSetDelegate);
      this.after("nodesChange", this._afterSetNodes);

      this._bindDelegate();
    },

    /*
     * syncUI is used to update the rendered DOM, based on the current
     * Tooltip state
     */
    syncUI: function () {
      this._uiSetNodes(this.get("triggerNodes"));
    },

    /*
     * Public method, which can be used by triggerEvent event listeners
     * to set the content of the tooltip for the current trigger node
     */
    setTriggerContent: function (content) {
      var contentBox = this.get("contentBox");
      contentBox.set("innerHTML", "");

      if (content) {
        if (content instanceof Node) {
          for (var i = 0, l = content.size(); i < l; ++i) {
            contentBox.appendChild(content.item(i));
          }
        } else if (Lang.isString(content)) {
          contentBox.set("innerHTML", content);
        }
      }
    },

    /*
     * Default attribute change listener for
     * the triggerNodes attribute
     */
    _afterSetNodes: function (e) {
      this._uiSetNodes(e.newVal);
    },

    /*
     * Default attribute change listener for
     * the delegate attribute
     */
    _afterSetDelegate: function (e) {
      this._bindDelegate(e.newVal);
    },

    /*
     * Updates the rendered DOM to reflect the
     * set of trigger nodes passed in
     */
    _uiSetNodes: function (nodes) {
      if (this._triggerNodes) {
        this._triggerNodes.removeClass(this._triggerClassName);
      }

      if (nodes) {
        this._triggerNodes = nodes;
        this._triggerNodes.addClass(this._triggerClassName);
      }
    },

    /*
     * Attaches the default mouseover DOM listener to the
     * current delegate node
     */
    _bindDelegate: function () {
      var eventHandles = this._eventHandles;

      if (eventHandles.delegate) {
        eventHandles.delegate.detach();
        eventHandles.delegate = null;
      }
      eventHandles.delegate = Y.delegate("mouseenter", Y.bind(this._onNodeMouseEnter, this), this.get("delegate"), "." + this._triggerClassName);
    },

    /*
     * Default mouse enter DOM event listener.
     *
     * Delegates to the _enterTrigger method,
     * if the mouseover enters a trigger node.
     */
    _onNodeMouseEnter: function (e) {
      var node = e.currentTarget;
      if (node && (!this._currTrigger.node || !node.compareTo(this._currTrigger.node))) {
        this._enterTrigger(node, e.pageX, e.pageY);
      }
    },

    /*
     * Default mouse leave DOM event listener
     *
     * Delegates to _leaveTrigger if the mouse
     * leaves the current trigger node
     */
    _onNodeMouseLeave: function (e) {
      this._leaveTrigger(e.currentTarget);
    },

    /*
     * Default mouse move DOM event listener
     */
    _onNodeMouseMove: function (e) {
      this._overTrigger(e.pageX, e.pageY);
    },

    /*
     * Default handler invoked when the mouse enters
     * a trigger node. Fires the triggerEnter
     * event which can be prevented by listeners to
     * show the tooltip from being displayed.
     */
    _enterTrigger: function (node, x, y) {
      this._setCurrentTrigger(node, x, y);
      this.fire("triggerEnter", {node: node, pageX: x, pageY: y});
    },

    /*
     * Default handler for the triggerEvent event,
     * which will setup the timer to display the tooltip,
     * if the default handler has not been prevented.
     */
    _defTriggerEnterFn: function (e) {
      var node = e.node;
      if (!this.get("disabled")) {
        this._clearTimers();
        var delay = (this.get("visible")) ? 0 : this.get("showDelay");
        this._timers.show = Y.later(delay, this, this._showTooltip, [node]);
      }
    },

    /*
     * Default handler invoked when the mouse leaves
     * the current trigger node. Fires the triggerLeave
     * event and sets up the hide timer
     */
    _leaveTrigger: function (node) {
      this.fire("triggerLeave");

      this._clearCurrentTrigger();
      this._clearTimers();

      this._timers.hide = Y.later(this.get("hideDelay"), this, this._hideTooltip);
    },

    /*
     * Default handler invoked for mousemove events
     * on the trigger node. Stores the current mouse
     * x, y positions
     */
    _overTrigger: function (x, y) {
      this._currTrigger.mouseX = x;
      this._currTrigger.mouseY = y;
    },

    /*
     * Shows the tooltip, after moving it to the current mouse
     * position.
     */
    _showTooltip: function (node) {
      var x = this._currTrigger.mouseX;
      var y = this._currTrigger.mouseY;

      this.move(x + Tooltip.OFFSET_X, y + Tooltip.OFFSET_Y);

      this.show();
      this._clearTimers();

      this._timers.hide = Y.later(this.get("autoHideDelay"), this, this._hideTooltip);
    },

    /*
     * Hides the tooltip, after clearing existing timers.
     */
    _hideTooltip: function () {
      this._clearTimers();
      this.hide();
    },

    /*
     * Set the rendered content of the tooltip for the current
     * trigger, based on (in order of precedence):
     *
     * a). The string/node content attribute value
     * b). From the content lookup map if it is set, or
     * c). From the title attribute if set.
     */
    _setTriggerContent: function (node) {
      var content = this.get("content");
      if (content && !(content instanceof Node || Lang.isString(content))) {
        content = content[node.get("id")] || node.getAttribute("title");
      }
      this.setTriggerContent(content);
    },

    /*
     * Set the currently bound trigger node information, clearing
     * out the title attribute if set and setting up mousemove/out
     * listeners.
     */
    _setCurrentTrigger: function (node, x, y) {

      var currTrigger = this._currTrigger,
          triggerHandles = this._eventHandles.trigger;

      this._setTriggerContent(node);

      triggerHandles.mouseMove = Y.on("mousemove", Y.bind(this._onNodeMouseMove, this), node);
      triggerHandles.mouseOut = Y.on("mouseleave", Y.bind(this._onNodeMouseLeave, this), node);

      var title = node.getAttribute("title");
      node.setAttribute("title", "");

      currTrigger.mouseX = x;
      currTrigger.mouseY = y;
      currTrigger.node = node;
      currTrigger.title = title;
    },

    /*
     * Clear out the current trigger state, restoring
     * the title attribute on the trigger node,
     * if it was originally set.
     */
    _clearCurrentTrigger: function () {

      var currTrigger = this._currTrigger,
          triggerHandles = this._eventHandles.trigger;

      if (currTrigger.node) {
        var node = currTrigger.node;
        var title = currTrigger.title || "";

        currTrigger.node = null;
        currTrigger.title = "";

        triggerHandles.mouseMove.detach();
        triggerHandles.mouseOut.detach();
        triggerHandles.mouseMove = null;
        triggerHandles.mouseOut = null;

        node.setAttribute("title", title);
      }
    },

    /*
     * Cancel any existing show/hide timers
     */
    _clearTimers: function () {
      var timers = this._timers;
      if (timers.hide) {
        timers.hide.cancel();
        timers.hide = null;
      }
      if (timers.show) {
        timers.show.cancel();
        timers.show = null;
      }
    },

    /*
     * Detach any stored event handles
     */
    _clearHandles: function () {
      var eventHandles = this._eventHandles;

      if (eventHandles.delegate) {
        this._eventHandles.delegate.detach();
      }
      if (eventHandles.trigger.mouseOut) {
        eventHandles.trigger.mouseOut.detach();
      }
      if (eventHandles.trigger.mouseMove) {
        eventHandles.trigger.mouseMove.detach();
      }
    }
  }, {

    // STATIC METHODS/PROPERTIES

    OFFSET_X: 15,
    OFFSET_Y: 15,
    OFFSCREEN_X: OX,
    OFFSCREEN_Y: OY,

    ATTRS: {

      /*
       * The tooltip content. This can either be a fixed content value,
       * or a map of id-to-values, designed to be used when a single
       * tooltip is mapped to multiple trigger elements.
       */
      content: {
        value: null
      },

      /*
       * The set of nodes to bind to the tooltip instance. Can be a string,
       * or a node instance.
       */
      triggerNodes: {
        value: null,
        setter: function (val) {
          if (val && Lang.isString(val)) {
            val = Node.all(val);
          }
          return val;
        }
      },

      /*
       * The delegate node to which event listeners should be attached.
       * This node should be an ancestor of all trigger nodes bound
       * to the instance. By default the document is used.
       */
      delegate: {
        value: null,
        setter: function (val) {
          return Y.one(val) || Y.one("document");
        }
      },

      /*
       * The time to wait, after the mouse enters the trigger node,
       * to display the tooltip
       */
      showDelay: {
        value: 250
      },

      /*
       * The time to wait, after the mouse leaves the trigger node,
       * to hide the tooltip
       */
      hideDelay: {
        value: 3000
      },

      /*
       * The time to wait, after the tooltip is first displayed for
       * a trigger node, to hide it, if the mouse has not left the
       * trigger node
       */
      autoHideDelay: {
        value: 20000
      },

      /*
       * Override the default visibility set by the widget base class
       */
      visible: {
        value: false
      },

      /*
       * Override the default XY value set by the widget base class,
       * to position the tooltip offscreen
       */
      xy: {
        value: [OX, OY]
      }
    }
  });

  var tt = new Tooltip({
    triggerNodes: ".yui3-hastooltip",
    //delegate: "#delegate",
    content: {
      tt3: "Tooltip 3 (from lookup)"
    },
    shim: false,
    zIndex: 2
  });
  tt.render();

  tt.on("triggerEnter", function (e) {
    var node = e.node;
    if (node && node.get("id") == "tt2") {
      this.setTriggerContent("Tooltip 2 (from triggerEvent)");
    }
  });
}