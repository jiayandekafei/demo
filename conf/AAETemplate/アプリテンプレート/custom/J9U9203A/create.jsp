<%@ page pageEncoding="utf8" %><%@ include file="/WEB-INF/tags/include.tag" %><%@ taglib prefix="ad" tagdir="/WEB-INF/tags/custom/ad/" %><c:set var="actionType"><%= request.getParameter("actionType") %></c:set><c:set var="formId"><%= request.getParameter("formId") %></c:set><style type="text/css">.ad-free-layout {zoom: 1;}.ad-layout-table {table-layout: fixed;border-collapse: separate;border-spacing: 0;empty-cells: show;width: 100%;}.ad-layout-table-collapse .ad-layout-table {border-collapse: collapse;}.ad-layout-table .colinputitem {border-collapse: separate;}.ad-layout-table .inner-doclist {border-collapse: collapse;}.ad-layout-header {padding: 0;overflow: hidden;}.ad-field-label {font-weight: bold;text-align: right;vertical-align: top;}.ad-field-label label {display: block;}.ad-field-widget .ad-content,.ad-field-label .ad-content,.ad-static-text .ad-content,.ad-static-richtext .ad-content {padding: 3px 5px;}.edit .ad-field-label .ad-content,.edit .ad-static-text .ad-content,.edit .ad-static-richtext .ad-content {padding: 6px 5px;}.ad-field-widget,.ad-static-text,.ad-static-richtext,.ad-workflow-history, .ad-tab-form, .ad-fieldset {vertical-align: top;}.ad-field-widget .ad-content {position: relative;}* html .ad-field-widget .fielditem,* html .ad-field-widget .readOnly,* html .ad-field-widget .docline,* html .ad-field-widget .colinputitem,* html .ad-field-widget .colinput,* html .ad-field-widget .col {position: relative;}.ad-free-layout .colinputitem .colitem {display: none;}.ad-free-layout .colselectitem .colitem {display: block;}.ad-free-layout .colinputitem .colinput {width: 100% !important;}.ad-free-layout .colinputitem .colinput .col {margin: 0 !important;padding: 0 !important;width: 100% !important;}.ad-free-layout .colinputitem .colinput .col .signature {display: inline-table;}*:first-child+html .ad-field-widget .readOnly .colinput {margin-top: 0;margin-bottom: 0;padding-top: 3px;padding-bottom: 3px;}.ad-free-layout .currency,.ad-free-layout .aq-currency .aq-statictext-value {width: auto;}.ad-free-layout .resource-list {margin-top: 3px;}.ad-free-layout .docfooter-creator,.ad-free-layout .docfooter-created,.ad-free-layout .docfooter-modifier,.ad-free-layout .docfooter-modified {border-left: 0;margin: 0;padding: 0;float: none;}.ad-free-layout .docfooter-creator label,.ad-free-layout .docfooter-created label,.ad-free-layout .docfooter-modifier label,.ad-free-layout .docfooter-modified label {display: none;}.ad-free-layout .itemname label {display: inline;}.ad-free-layout .ad-required {color: #CC0000;font-weight: normal;}.ad-free-layout .ad-static-text {word-wrap: break-word;white-space: -moz-pre-wrap;white-space: pre-wrap;}.ad-free-layout .ad-field-widget .colinputitem,.ad-free-layout .ad-field-widget .colinputitem .colinput {text-decoration: inherit;}.ad-free-layout .ad-field-widget input,.ad-free-layout .ad-field-widget textarea,.ad-free-layout .ad-field-widget select,.ad-free-layout .ad-field-widget pre,.ad-free-layout .ad-static-text pre {font-size: 100%;font-family: inherit;}.ad-free-layout .ad-field-widget .col p,.ad-free-layout .ad-static-richtext p {margin-top: 0;margin-bottom: 0;}.ad-free-layout .ad-align-left .currency {text-align: left;}.ad-free-layout .ad-align-center .currency {text-align: center;}.ad-free-layout .ad-align-center .selectcontainer,.ad-free-layout .ad-align-right .selectcontainer {float: none;display: inline-block;}.ad-field-widget .selectform .itemname .icon,.ad-field-widget .docfile .border-button {float: none;display: inline-block;width: auto;}* html .ad-free-layout .ad-align-center .selectcontainer,* html .ad-free-layout .ad-align-right .selectcontainer,* html .ad-field-widget .selectform .itemname .icon,* html .ad-field-widget .docfile .border-button {display: inline;zoom: 1;}*+html .ad-free-layout .ad-align-center .selectcontainer,*+html .ad-free-layout .ad-align-right .selectcontainer,*+html .ad-field-widget .selectform .itemname .icon,*+html .ad-field-widget .docfile .border-button {display: inline;zoom: 1;}.ad-free-layout .ad-align-center .selectform .itemname,.ad-free-layout .ad-align-right .selectform .itemname {text-align: left;}.ad-free-layout .ad-weight-normal .doctitle .colinput .col {font-weight: normal;}.ad-field-widget .selectform .itemname label,.ad-field-widget .selectform .itemname pre {width: auto;}.ad-field-widget .textlist .selectform .itemname,.ad-field-widget .docfile .selectform .itemname {float: none;}.ad-align-center .textlist .selectform .itemname,.ad-align-center .docfile .selectform .itemname {text-align: center;}.ad-align-right .textlist .selectform .itemname,.ad-align-right .docfile .selectform .itemname {text-align: right;}.ad-field-widget .error-message,.ad-field-widget .resource-list-suggest .placeholder {text-align: left;}.ad-free-layout .ad-text-underline .col,.ad-free-layout .ad-text-underline .aq-statictext-value,.ad-free-layout .ad-text-underline input,.ad-free-layout .ad-text-underline select,.ad-free-layout .ad-text-underline option,.ad-free-layout .ad-text-underline textarea,.ad-free-layout .ad-text-underline .inputcheck,.ad-free-layout .ad-text-underline .inputdate,.ad-free-layout .ad-text-underline .icon,.ad-free-layout .ad-text-underline .itemname,.ad-free-layout .ad-text-underline .actionmenu-btn {text-decoration: underline;}.ad-free-layout .ad-text-overline .col,.ad-free-layout .ad-text-overline .aq-statictext-value,.ad-free-layout .ad-text-overline input,.ad-free-layout .ad-text-overline select,.ad-free-layout .ad-text-overline option,.ad-free-layout .ad-text-overline textarea,.ad-free-layout .ad-text-overline .inputcheck,.ad-free-layout .ad-text-overline .inputdate,.ad-free-layout .ad-text-overline .icon,.ad-free-layout .ad-text-overline .itemname,.ad-free-layout .ad-text-overline .actionmenu-btn {text-decoration: overline;}.ad-free-layout .ad-text-line-through .col,.ad-free-layout .ad-text-line-through .aq-statictext-value,.ad-free-layout .ad-text-line-through input,.ad-free-layout .ad-text-line-through select,.ad-free-layout .ad-text-line-through option,.ad-free-layout .ad-text-line-through textarea,.ad-free-layout .ad-text-line-through .inputcheck,.ad-free-layout .ad-text-line-through .inputdate,.ad-free-layout .ad-text-line-through .icon,.ad-free-layout .ad-text-line-through .itemname,.ad-free-layout .ad-text-line-through .actionmenu-btn {text-decoration: line-through;}.ad-free-layout .ad-text-underline-overline .col,.ad-free-layout .ad-text-underline-overline .aq-statictext-value,.ad-free-layout .ad-text-underline-overline input,.ad-free-layout .ad-text-underline-overline select,.ad-free-layout .ad-text-underline-overline option,.ad-free-layout .ad-text-underline-overline textarea,.ad-free-layout .ad-text-underline-overline .inputcheck,.ad-free-layout .ad-text-underline-overline .inputdate,.ad-free-layout .ad-text-underline-overline .icon,.ad-free-layout .ad-text-underline-overline .itemname,.ad-free-layout .ad-text-underline-overline .actionmenu-btn {text-decoration: underline overline;}.ad-free-layout .ad-text-underline-line-through .col,.ad-free-layout .ad-text-underline-line-through .aq-statictext-value,.ad-free-layout .ad-text-underline-line-through input,.ad-free-layout .ad-text-underline-line-through select,.ad-free-layout .ad-text-underline-line-through option,.ad-free-layout .ad-text-underline-line-through textarea,.ad-free-layout .ad-text-underline-line-through .inputcheck,.ad-free-layout .ad-text-underline-line-through .inputdate,.ad-free-layout .ad-text-underline-line-through .icon,.ad-free-layout .ad-text-underline-line-through .itemname,.ad-free-layout .ad-text-underline-line-through .actionmenu-btn {text-decoration: underline line-through;}.ad-free-layout .ad-text-overline-line-through .col,.ad-free-layout .ad-text-overline-line-through .aq-statictext-value,.ad-free-layout .ad-text-overline-line-through input,.ad-free-layout .ad-text-overline-line-through select,.ad-free-layout .ad-text-overline-line-through option,.ad-free-layout .ad-text-overline-line-through textarea,.ad-free-layout .ad-text-overline-line-through .inputcheck,.ad-free-layout .ad-text-overline-line-through .inputdate,.ad-free-layout .ad-text-overline-line-through .icon,.ad-free-layout .ad-text-overline-line-through .itemname,.ad-free-layout .ad-text-overline-line-through .actionmenu-btn {text-decoration: overline line-through;}.ad-free-layout .ad-text-underline-overline-line-through .col,.ad-free-layout .ad-text-underline-overline-line-through .aq-statictext-value,.ad-free-layout .ad-text-underline-overline-line-through input,.ad-free-layout .ad-text-underline-overline-line-through select,.ad-free-layout .ad-text-underline-overline-line-through option,.ad-free-layout .ad-text-underline-overline-line-through textarea,.ad-free-layout .ad-text-underline-overline-line-through .inputcheck,.ad-free-layout .ad-text-underline-overline-line-through .inputdate,.ad-free-layout .ad-text-underline-overline-line-through .icon,.ad-free-layout .ad-text-underline-overline-line-through .itemname,.ad-free-layout .ad-text-underline-overline-line-through .actionmenu-btn {text-decoration: underline overline line-through;}.ad-free-layout .ad-empty-item {text-decoration: none !important;}.ad-free-layout .userichtext label,.ad-free-layout .adjust-area .icon {text-decoration: none;}.ad-workflow-history-list {border-collapse: separate;empty-cells: show;width: 100%;border-top: 1px #CCCCCC solid;border-left: 1px #CCCCCC solid;border-right: 0 none;border-bottom: 0 none;margin: 0;padding: 0;table-layout: fixed;page-break-inside: avoid;word-wrap: break-word;}.ad-workflow-history-list th,.ad-workflow-history-list td {border-top: 0 none;border-left: 0 none;border-right: 1px #CCCCCC solid;border-bottom: 1px #CCCCCC solid;}.ad-workflow-history-list th {width: 7em;}.ad-workflow-history-attachment .itemname a {white-space: pre-wrap;}.ad-workflow-history-attachment .preview-img {display: none;}.ad-workflow-history-comment td {white-space: pre-wrap;}html>/**/body .ad-static-image .ad-content,html>/**/body .ad-static-image .ad-content img {vertical-align: inherit;}.ad-hide-invisible .ad-hidden {height: 0 !important;font-size: 0 !important;border-width: 0 0 0 0 !important;padding: 0 !important;background-color: transparent !important;background-image: none !important;overflow: hidden !important;}.ad-hide-invisible .ad-hidden * {display: none !important;}.ad-hide-invisible .ad-hidden-row {display: none !important;}.ad-loading-msg {display: none;}.popup .ad-loading-msg {display: block;margin: -1.2em -1em;}.ad-dummy {font-size: 0;height: 0;overflow: hidden;text-decoration: none;display: none;visibility: hidden;}* html body .ad-dummy {display: block;}*:first-child+html body .ad-dummy {display: block;}.ad-page-break {font-size: 0;height: 0;overflow: hidden;text-decoration: none;page-break-after: always;}.ad-fieldset-plus,.ad-fieldset-minus {width: 16px;height: 16px;float: left;margin: 0;padding: 0;}.ad-hide-invisible .ad-fieldset-close {height: auto !important;}.sub-category-body {zoom: 1;}@media print {.ad-layout-table {width: 100% !important;}.custom .docwrapper {padding: 0 !important;}html>/**/body .ad-free-layout {padding: 1px;}.ad-field-widget.ad-inprintable * {visibility: hidden;}.ad-hide-invisible .ad-inprintable {height: 0 !important;font-size: 0 !important;border-width: 0 0 0 0 !important;padding: 0 !important;background-color: transparent !important;background-image: none !important;overflow: hidden !important;}.ad-hide-invisible .ad-inprintable * {display: none !important;}.ad-workflow-history-revision {display: none;}.sub-category-body {display: block; !important;}}</style><!--[if lt IE 8]><style>.ad-layout-row {background: none;}</style><![endif]--><!--[if lt IE 8]><style>.ad-free-layout .ad-field-widget input,.ad-free-layout .ad-field-widget textarea,.ad-free-layout .ad-field-widget select,.ad-free-layout .ad-field-widget pre,.ad-free-layout .ad-static-text pre {behavior: expression(!this.style || this.style.behavior || (this.style.fontFamily = this.parentNode.currentStyle.fontFamily, this.style.behavior = 'none'));}</style><![endif]--><div id="ad_layout_${formId}_${actionType}" class="ad-free-layout ad-hide-invisible" style="display: none"><table class="ad-layout-table ad-layout-table-collapse" cellspacing="0" cellpadding="0" style="background-color: #F0F0FF;border-collapse: collapse;"><colgroup><col style="width: 26.0mm;" /><col style="width: 114.0mm;" /></colgroup><c:if test="${context.ua.IE and context.ua.IEVersion >= 9}"><thead><tr class="ad-layout-row ad-layout-row-hd"><th class="ad-layout-header" style="width: 26.0mm;"></th><th class="ad-layout-header" style="width: 114.0mm;"></th></tr></thead></c:if><tbody><tr class="ad-layout-row"><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdapptitle" colspan="2" cellStyle="background-color:#000080;color:#FFFFFF;font-weight:bold;vertical-align:middle;font-size:18px;text-align:center;height:32px;" appendClass="ad-align-center" /></tr><tr class="ad-layout-row"><td class="ad-empty-item" colspan="2" style="height: 8px;"><div class="ad-content">&nbsp;</div></td></tr><tr class="ad-layout-row"><ad:field-label formId="${formId}" actionType="${actionType}" context="${context}" fieldId="title" cellStyle="border-top:1px #969696 solid;background-color:#B9B9FF;border-right:1px #969696 solid;color:#111114;font-weight:normal;border-left:1px #969696 solid;border-bottom:1px #969696 solid;text-align:left;height:16px;" /><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="title" cellStyle="border-top:1px #969696 solid;background-color:#FFFFFF;border-right:1px #969696 solid;border-left:1px #969696 solid;font-weight:normal;border-bottom:1px #969696 solid;height:16px;" appendClass="ad-weight-normal" /></tr><tr class="ad-layout-row"><ad:field-label formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdattachment" cellStyle="border-top:1px #969696 solid;background-color:#B9B9FF;border-right:1px #969696 solid;color:#111114;font-weight:normal;border-left:1px #969696 solid;border-bottom:1px #969696 solid;text-align:left;height:16px;" /><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdattachment" cellStyle="border-top:1px #969696 solid;background-color:#FFFFFF;border-right:1px #969696 solid;border-left:1px #969696 solid;border-bottom:1px #969696 solid;height:16px;" /></tr><tr class="ad-layout-row"><td class="ad-empty-item" colspan="2" style="height: 8px;"><div class="ad-content">&nbsp;</div></td></tr><tr class="ad-layout-row"><td class="ad-fieldset" colspan="2" style=""><div class="ad-content"><aqua:sub-category-form viewType="open"><jsp:attribute name="label"><aq:message key="閲覧者制限" escapeXml="true" /></jsp:attribute><jsp:body><table class="ad-layout-table" cellspacing="0" cellpadding="0"><colgroup><col style="width: 12.4mm;" /><col style="width: 57.6mm;" /></colgroup><c:if test="${context.ua.IE and context.ua.IEVersion >= 9}"><thead><tr class="ad-layout-row ad-layout-row-hd"><th class="ad-layout-header" style="width: 12.4mm;"></th><th class="ad-layout-header" style="width: 57.6mm;"></th></tr></thead></c:if><tbody><tr class="ad-layout-row"><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdrestricted" colspan="2" cellStyle="height:16px;" /></tr><tr class="ad-layout-row"><ad:field-label formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdrange_rids" cellStyle="border-top:1px #969696 solid;background-color:#B9B9FF;border-right:1px #969696 solid;color:#111114;border-left:1px #969696 solid;font-weight:normal;border-bottom:1px #969696 solid;text-align:left;height:16px;" /><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdrange_rids" cellStyle="border-top:1px #969696 solid;background-color:#FFFFFF;border-right:1px #969696 solid;border-left:1px #969696 solid;border-bottom:1px #969696 solid;height:16px;" /></tr><tr class="ad-layout-row"><ad:field-label formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdrange_names" cellStyle="border-top:1px #969696 solid;background-color:#B9B9FF;border-right:1px #969696 solid;color:#111114;border-left:1px #969696 solid;font-weight:normal;border-bottom:1px #969696 solid;text-align:left;height:16px;" /><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdrange_names" cellStyle="border-top:1px #969696 solid;background-color:#FFFFFF;border-right:1px #969696 solid;border-left:1px #969696 solid;border-bottom:1px #969696 solid;height:16px;" /></tr><tr class="ad-layout-row"><ad:field-label formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdreader_selected_u" cellStyle="border-top:1px #969696 solid;background-color:#B9B9FF;border-right:1px #969696 solid;color:#111114;border-left:1px #969696 solid;font-weight:normal;border-bottom:1px #969696 solid;text-align:left;height:16px;" /><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdreader_selected_u" cellStyle="border-top:1px #969696 solid;background-color:#FFFFFF;border-right:1px #969696 solid;border-left:1px #969696 solid;border-bottom:1px #969696 solid;height:16px;" /></tr><tr class="ad-layout-row"><ad:field-label formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdreader_selected_g" cellStyle="border-top:1px #969696 solid;background-color:#B9B9FF;border-right:1px #969696 solid;color:#111114;border-left:1px #969696 solid;font-weight:normal;border-bottom:1px #969696 solid;text-align:left;height:16px;" /><ad:field-widget formId="${formId}" actionType="${actionType}" context="${context}" fieldId="fdreader_selected_g" cellStyle="border-top:1px #969696 solid;background-color:#FFFFFF;border-right:1px #969696 solid;border-left:1px #969696 solid;border-bottom:1px #969696 solid;height:16px;" /></tr></tbody></table></jsp:body></aqua:sub-category-form></div></td></tr></tbody></table></div><div id="ad_loading_${formId}_${actionType}" class="loading-msg ad-loading-msg"><span>Loading...</span></div><script type="text/javascript">(function(){var m=/MSIE\s+(\d+(\.\d+)?)/.test(navigator.userAgent)&&parseFloat(RegExp.$1,10)<9,h="${actionType.escapeJs}",y="${formId.escapeJs}",c="ad_fieldwidget_"+h+"_",f="ad_fieldlabel_"+h+"_",u=document.getElementById("ad_layout_"+y+"_"+h),e=document.getElementById("ad_loading_"+y+"_"+h),q=[],l=null,x=true,r,t;if(m){r=function(E,B,D){var C=[],A=B||0,z=D||E.length;for(;A<z;A++){C.push(E[A])}return C}}else{r=function(B,z,A){return Array.prototype.slice.call(B,z||0,A||B.length)}}t=Array.prototype.indexOf||function(B){var A=0,z=this.length;for(;A<z;A++){if(this[A]===B){return A}}return -1};function b(A,z){var B=A.className;return B&&(new RegExp("(^|\\s)"+z+"(\\s|$)")).test(B)}function i(A,z){var B=A.className;if(!(new RegExp("(^|\\s)"+z+"(\\s|$)")).test(B)){A.className=B?B+" "+z:z}}function k(A,z){A.className=A.className.replace(new RegExp("(^|\\s+)"+z+"(\\s|$)")," ").replace(/(^\\s+|\\s+$)/g,"")}function d(A,D,C){var B,z;if(typeof A.forEach=="function"){A.forEach(D,C)}else{for(B=0,z=A.length;B<z;B++){D.call(C,A[B],B,A)}}}function s(A){var z=[],B=A.firstChild;for(;B;B=B.nextSibling){if(B.nodeType==1){z.push(B)}}return z}function a(B,C){var z,A;if(typeof B.innerText!="undefined"){B.innerText=C}else{B.textContent="";z=C.split("\n");for(A=0;A<z.length;A++){if(A>0){B.appendChild(document.createElement("br"))}B.appendChild(document.createTextNode(z[A]))}}}function v(E){var F=null,D,B,C,A,z;for(;E;E=E.parentNode){if(E.nodeType==1&&(E.tagName||"").toLowerCase()=="table"){F=E;break}}if(F){D=0;B=F.rows[0].cells;for(C=0,A=B.length;C<A;C++){z=B[C];D+=z.colSpan||1}return D}return -1}function g(z){var A=(z.tagName||"").toLowerCase()=="tr"?z:z.parentNode;if(t.call(q,A)<0){q.push(A);if(x&&l==null){l=setTimeout(o,0)}}}function o(){var A=[],O=[],P,z,E,C,H,J,M,L,N,B,K,G,I,D,F;do{for(G=0,I=q.length;G<I;G++){P=q[G];z=false,E=false;C=0;for(H=P.firstChild;H;H=H.nextSibling){if(H.nodeType==1){if(!b(H,"ad-hidden")){z=true;break}if(H.rowSpan>1){E=true;break}C+=H.colSpan||1}}if(z||E||C!=v(P)){k(P,"ad-hidden-row")}else{i(P,"ad-hidden-row")}J=P.parentNode;while(J&&J!=u&&(J.tagName||"").toLowerCase()!="td"){J=J.parentNode}if(J&&b(J,"ad-fieldset")&&t.call(A,J)<0){A.push(J)}}q.length=0;for(G=0,I=A.length;G<I;G++){M=A[G];L=j(M);N=L?L.rows:[];B=false;for(D=0,F=N.length;D<F;D++){P=N[D];if(!b(P,"ad-hidden-row")&&!b(P,"ad-layout-row-hd")){B=true;break}}if(B){if(b(M,"ad-hidden")){k(M,"ad-hidden");g(M);O.push(L)}}else{if(!b(M,"ad-hidden")){i(M,"ad-hidden");g(M)}}}A.length=0}while(q.length>0);if(m&&(I=O.length)>0){K=document.createTextNode(" ");for(G=0;G<I;G++){L=O[G];L.appendChild(K);L.removeChild(K)}}l=null}function j(A){var z,C,B;if(A.querySelector){return A.querySelector("table.ad-layout-table")}z=s(A);for(;C=z.shift();){if((C.tagName||"").toLowerCase()=="table"&&b(C,"ad-layout-table")){return C}B=s(C);if(B.length>0){z.push.apply(z,B)}}return null}function p(C){var D=false,B=s(C),A=0,z=B.length,E;for(;A<z;A++){E=B[A];if(E.nodeType==1&&E.style.display!="none"&&(!b(E,"ad-content")||p(E))&&!b(E,"ad-dummy")){D=true;break}}return D}function n(){u.style.display="";e&&e.parentNode.removeChild(e)}
<c:if test="${!empty actionType and actionType ne 'view'}">
;function w(B,I,E,H,J,K){var C,z,F,A,G,D;switch(H){case"visible":case"editable":D=J.visible||J.editable;C=document.getElementById(c+E+I);if(C){(D?k:i)(C,"ad-hidden");g(C)}z=document.getElementById(f+E+I);if(z){(D?k:i)(z,"ad-hidden");g(z)}break;case"title":z=document.getElementById(f+E+I);if(z){F=z.getElementsByTagName("label");if(F&&F.length>0){a(F[0],J.title)}}break;case"description":C=document.getElementById(c+E+I);if(C){C.setAttribute("title",J.description)}z=document.getElementById(f+E+I);if(z){z.setAttribute("title",J.description)}break;case"validator":z=document.getElementById(f+E+I);if(z){A=false;d(J.validators||[],function(L){if(L.visible&&L.enabled&&(L.name=="NotNullValidator"||(L.name=="ListSizeMinValidator"&&L.conditions&&L.conditions[0]>0))){A=true}});F=z.getElementsByTagName("label");if(F&&F.length>0){G=null;d(r(F[0].getElementsByTagName("span")),function(L){if(b(L,"ad-required")){if(A){G=L}else{L.parentNode.removeChild(L)}}});if(A&&!G){G=document.createElement("span");G.className="ad-required";a(G,"(*)");F[0].appendChild(G)}}}break}}if(typeof Depender!=="undefined"&&Depender.prototype){if(Depender.prototype.setCompletedHandler){Depender.prototype.setCompletedHandler("ad-free-layout",w)}else{if(!Depender.prototype._handleElem){Depender.prototype._handleElem=Depender.prototype.handleElem;Depender.prototype.handleElem=function(D,E,A,C,z,B){this._handleElem(D,E,A,C,z,B);w(D,E,A,C,z)}}}}
</c:if>
;if(u){x=false;d(r(u.getElementsByTagName("td")),function(z){var A;if(!b(z,"ad-field-widget")){return}if(!p(z)){i(z,"ad-hidden");g(z);A=document.getElementById(z.id.replace(/^ad_fieldwidget_/,"ad_fieldlabel_"));if(A){i(A,"ad-hidden");g(A)}}});if(q.length>0){o()}x=true;n()}})();</script>