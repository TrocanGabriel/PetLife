/*! 363-145-RELEASE 2019-06-25 */

!function(t){t.TRC=t.TRC||{};var e=function(t){var e=[];for(var n in t)t.hasOwnProperty(n)&&e.push(encodeURIComponent(n)+"="+encodeURIComponent(t[n]));return e.join("&")},n=function(){return!0},i=function(t,n,i,r){var o=t+"/"+encodeURIComponent(i||TRC.publisherId)+"/log/3"+"/"+n;return r&&(o+="?"+e(r)),o},r=function(e,i){var r,o=new(t.XDomainRequest||t.XMLHttpRequest);return o.open(e,i),o.onload=n,o.onerror=n,o.ontimeout=n,o.onprogress=n,o.withCredentials=!0,o};TRC.TRCLogger={post:function(t,n,o,a,s){var c=i(t,n,a,s),u=r("POST",c);u.setRequestHeader&&u.setRequestHeader("Content-Type","application/x-www-form-urlencoded"),u.send(e(o))},get:function(t,e,n,o){var a=i(t,e,o,n),s;r("GET",a).send()}}}(window),function(t){t._tfa=t._tfa||[],t._tfa.TfaConfig=function(t){this.config=t},t._tfa.TfaConfig.prototype={safeGet:function(t,e){var n;try{return this.config?void 0===(n=this.config[t])?e:n:e}catch(t){return e}},hasValidConfig:function(){return"string"!=typeof this.config&&!(this.config instanceof String)}},t._tfa.config=t._tfa.config||new t._tfa.TfaConfig({"tfa:trk:enabled":true}),t._taboola=t._taboola||[]}(window),function(t,e,n,i,r,o,a,s,c,u,l,f){"use strict";var h=null,d=null,g=null,p=null,m=null,v=null,S=null,T=function(){try{localStorage.setItem("taboolaStorageDetection","detect"),localStorage.removeItem("taboolaStorageDetection")}catch(t){return!1}return!0},w=function(t,e,n){var i=t.split(e);return i.slice(0,n-1).concat(i.length>=n?i.slice(n-1).join(e):[])},y=function(t){if(!t)throw new Error("Invalid URL!");this.href=t;var e=w(t,"#",2);return this.hash=e.length>1?"#"+e.pop():"",t=e[0],e=w(t,"?",2),this.search=e.length>1?"?"+e.pop():"",t=e[0],e=w(t,"://",2),this.protocol=e.length>1?e.shift()+":":"",t=e[0],e=w(t,"/",2),this.pathname=e.length>1?"/"+e.pop():"/",t=e[0],e=w(t,"@",2),this.auth=e.length>1?e.shift():"",t=e[0],e=w(t,":",2),this.port=e.length>1?parseInt(e.pop()):0,this.host=e[0],this},R={"http:":1,"https:":1};y.prototype.toString=function(t){return(this.host?this.protocol+"//"+(this.auth?this.auth+"@":"")+this.host+(this.port?":"+this.port:""):"")+this.pathname+this.search+(t?"":this.hash||"")},y.prototype.switchProtocol=function(t,e){var n=this instanceof y?this:new y(this),i;return R[t]&&(e&&"https:"==n.protocol||(n.protocol=t)),(i=n.toString(!1)).length>1?i:""},y.prototype.getParameter=function(t){for(var e,n=(this instanceof y?this:new y(this)).search.substr(1).split(/&/),i=0;i<n.length;i++){var r=n[i].split(new RegExp("="),2);if(unescape(r[0])==t)return unescape(r[1])}return null};var b={states:{ABP_DETECTION_DISABLED:-2,ABP_NOT_DETECTED:0,ABP_DETECTED:1},createBlockDetectionDiv:function(t){var n=e.createElement("div");return n.className=t,n.style.fontSize="initial",n.appendChild(e.createTextNode(".")),e.documentElement.appendChild(n),n},isBlockDetectedOnDiv:function(t){return!t.offsetHeight},isBlockDetectedOnClassNames:function(t){var n,i=t.length,r;for(n=0;n<i;n++)if(t[n]){r=this.createBlockDetectionDiv(t[n]);try{if(this.isBlockDetectedOnDiv(r))return!0}catch(t){}finally{e.documentElement.removeChild(r)}}return!1},getBlockedState:function(t){return K()||$()?this.states.ABP_NOT_DETECTED:t&&this.isBlockDetectedOnClassNames(t)?this.states.ABP_DETECTED:this.states.ABP_NOT_DETECTED}},C={map:function(t,e){if("function"==typeof Array.prototype.map)return e.map(t);for(var n=[],i=0;i<e.length;i++)n.push(t(e[i],i,e));return n},forEach:function(t,e){if("function"==typeof Array.prototype.forEach)return e.forEach(t);for(var n=0;n<e.length;n++)t(e[n],n,e)},filter:function(t,e){if("function"==typeof Array.prototype.filter)return e.filter(t);for(var n=[],i=0;i<e.length;i++)t(e[i],i,e)&&n.push(e[i]);return n}},I,E,_,N;C.objKeys=Object.keys||(I=Object.prototype.hasOwnProperty,E=!{toString:null}.propertyIsEnumerable("toString"),N=(_=["toString","toLocaleString","valueOf","hasOwnProperty","isPrototypeOf","propertyIsEnumerable","constructor"]).length,function(t){if("object"!=typeof t&&("function"!=typeof t||null===t))throw new TypeError("Object.keys called on non-object");var e=[],n,i;for(n in t)I.call(t,n)&&e.push(n);if(E)for(i=0;i<N;i++)I.call(t,_[i])&&e.push(_[i]);return e});var O=function(t,e){var n;for(n in t)t.hasOwnProperty(n)&&e.push([n,t[n]])},D=function(){var t=new Date,e=t.getHours(),n=t.getMinutes(),i=t.getSeconds()+t.getMilliseconds()/1e3;return(e<10?"0":"")+e+":"+(n<10?"0":"")+n+":"+(i<10?"0":"")+i.toFixed(3)},P=function(){TRC.trkCallback=function(){},p.parentNode.removeChild(p),p=null},M=function(){P(),TRC.trkRequestStatus=!1},k=function(t,n,i,r){var o=e.getElementsByTagName("script")[0],a=e.createElement("script");return a.type="text/javascript",a.src=t,a.charset="UTF-8",i?a.setAttribute("defer","defer"):r&&a.setAttribute("async","async"),"function"==typeof n&&(a.addEventListener?(a.addEventListener("load",n,!1),a.addEventListener("error",n,!1)):a.onreadystatechange=function(){"loaded"!==a.readyState&&"complete"!=a.readyState||n.apply(a)}),o.parentNode.insertBefore(a,o),a},U=function(){p=k("//trc.taboola.com/"+v+"/trc/3/json?tim="+D()+"&data="+encodeURIComponent(JSON.stringify(A()))),m=t.setTimeout(M,5e3)},A=function(){var t=x();return TRC.trkCallback=V,t.cb="TRC.trkCallback",t},V=function(e){t.clearTimeout(m),e&&e.trc&&(e.trc.ui&&(e.trc["DNT"]&&"TRUE"===e.trc["DNT"].toUpperCase()?localStorage.removeItem("taboola global:user-id"):localStorage.setItem("taboola global:user-id",e.trc["ui"])),e.trc.sd&&localStorage.setItem(v+":session-data",e.trc["sd"]),TRC.events_ri=e.trc["vl"]&&e.trc["vl"].length?e.trc["vl"][0].ri:TRC.events_ri),TRC.trkRequestStatus=!0,TRC.alertVVResponseLoaded&&TRC.alertVVResponseLoaded(i),P()},x=function(){return{id:~~(1e3*Math.random()),ii:H(),it:Q(t._taboola),sd:Z(),ui:tt(),vi:L(),cv:i,uiv:"default",u:et(),e:W(),mpv:t._tfa&&t._tfa.config.safeGet("tfa:trk:create-media-page-view",!0),r:[{li:"rbox-tracking",s:0,uim:"rbox-tracking:pub="+n+":abp="+b.getBlockedState(["banner_ad","sponsored_ad"]),uip:"rbox-tracking",orig_uip:"rbox-tracking"}]}},L=function(){return t.taboola_view_id||(t.taboola_view_id=(new Date).getTime()),t.taboola_view_id},F=function(t){for(var e in t)if(t.hasOwnProperty(e))for(var n in C.objKeys(t[e])){var i=C.objKeys(t[e])[n],r=t[e][i];if("unknown"!=r)switch("auto"==r&&(r=""),i){case"video":h=r;break;case"url":g=r;break;case"article":case"category":case"home":case"search":case"photo":case"other":case"content_hub":d=r;break;case"ref_url":S=r}}},j=function(){return TRC.events_ri},H=function(){var e=null;return h||""==h?e=h:(d||""==d)&&(e=d),(""==e||t._tfa&&t._tfa.config.hasValidConfig())&&(e=q("item-id",r,o)),e},B=function(t,e,n){var i;if(!n)return e;C.forEach(function(t){var n=e.search(t);n>=0&&(e=e.substr(0,n))},n["truncate-at"]||[]);var r=new y(e);for(var o in n)if(n.hasOwnProperty(o)){if(!n[o])continue;switch(o){case"host":delete r.host;break;case"trailing-dirsep":for(;"/"==r.pathname.substr(r.pathname.length-1);)r.pathname=r.pathname.substr(0,r.pathname.length-1);break;case"query":var a=[],s=r.search.replace(/^\?/,"").split("&");"string"==typeof(i=n[o])&&(i=new RegExp(i));var c=i instanceof Array?function(t){for(var e=0;e<i.length;e++)if(t==i[e])return!0;return!1}:i instanceof RegExp?i.test.trcBind(i):function(){return!1};s.forEach(function(t){c(decodeURIComponent(t.split("=")[0]))&&a.push(t)}),r.search=(a.length?"?":"")+a.join("&");break;case"fragment":var u=r.hash.replace(/^#/,"");"string"==typeof(i=n[o])&&(i=new RegExp(i)),r.hash="",i instanceof RegExp&&i.test(u)?r.hash="#"+u:i instanceof Array&&i.forEach(function(t){u.search(t)>=0&&(r.hash="#"+u)})}}return r.pathname||(r.pathname="/"),"item-id"==t?r.toString().toLowerCase():r.toString()},K=function(){return J(W(),"ampproject.net")},$=function(){return J(W(),"instantarticles.fb.com")},J=function(t,e){try{return void 0!==t&&void 0!==e&&t.indexOf(e)>0}catch(t){return!1}},W=function(){var t="",e=function(){for(var t=document.head.getElementsByTagName("link"),e=0;e<t.length;e++)if("referrer"==t[e].rel)return t[e].href;return null}(),n=S||e;try{t=decodeURI(top.window.document.referrer)}catch(t){}return n||(t&&!/https?:\/\/(\w+)\.taboola(syndication)?\.com/.test(t)?t.substr(0,400):t.split("?")[0])},q=function(t,e,n){var i,r={paramUrl:z,canonical:G,og:Y,location:X},o=c||["paramUrl","meta","canonical","og","location"],a=0,s,u,l,f="",d=function(t,e){return B.call(this,t,e,n)};for(o.push("location");a<o.length&&(!f||/^\s*$/.test(f));)u=o[a],f=(s=r[o[a]])?s.call(null,t,d):null,a++;return"item-url"===t&&u===z?f:(l=u!==X,f=e?e.call(this,f,h?"video":"text",l):f)},z=function(t,e){return!g||"item-id"!==t&&"item-url"!==t?null:e.call(this,t,g)},G=function(t,n){for(var i=e.head.getElementsByTagName("link"),r=0;r<i.length;r++)if("canonical"==i[r].rel)return n.call(this,t,i[r].href);return null},Y=function(t,n){for(var i=e.head.getElementsByTagName("meta"),r=0;r<i.length;r++)if("og:url"==i[r].getAttribute("property")&&i[r].content.length>5)return n.call(this,t,i[r].content);return null},X=function(e,n){var i=function(){var e=t.location,n=C.filter(function(t){return 0!==t.search("trc_")&&"taboola-debug"!==t},e.search.replace(/^\?/,"").split("&")).join("&");return e.origin+e.pathname+"?"+n};return n.call(this,e,i())},Q=function(t){try{var e=Object.keys(t[0]);for(var n in e)switch(e[n]){case"home":return"home";case"category":return"category";case"text":case"article":return"text";case"search":return"search";case"photo":return"photo";case"other":return"other";case"content_hub":return"content_hub";case"video":default:return"video"}}catch(t){return"video"}},Z=function(){return localStorage.getItem(v+":session-data")},tt=function(){return localStorage.getItem("taboola global:user-id")},et=function(){return q("item-url",s,a)},nt=function(t){for(var e,n=/^(.*\/libtrc\/.+\/)(?:(?:trk)|(?:tfa))\.js(?:\?(.*))?$/,i=0;i<t.length;i++)if(e=t[i].src.match(n))return e[1]},it=function(){for(var t=nt(e.getElementsByTagName("script")),n=[{key:"?",index:0},{key:"://",index:1},{key:"//",index:1},{key:"/",index:0}],i=0,r=n.length,o=t,a;i<r;i++)o=(a=w(o,n[i].key,2)).length>1?a[n[i].index]:a[0];return o},rt=function(){if(!TRC.AdServerManager){var t=it();TRC.VVReady=ot,k("//"+t+"/libtrc/vv."+i+".js")}},ot=function(){TRC.adManager=new TRC.AdServerManager(l,i),TRC.adManager.startVV().then(function(){TRC.adManager.run()})},at=function(t){if(0===C.objKeys(t).length)return n;var e=document.createElement("a");e.href=g;var i=(e.host||location.host).toLowerCase(),r=(e.href||location.href).toLowerCase(),o=t[i],a="/",s=["m","mobile","www2","www3"],c,u,l,f,h,d;if(!o){for(O(t,c=[]),c.sort(function(t,e){return t[0].length>e[0].length?-1:t[0].length<e[0].length?1:0}),u=0,l=c.length;u<l;u++)if((f=c[u][0].toLowerCase()).indexOf(a)>0){if(r.match(f)){o=c[u][1];break}if(f.indexOf("www.")>-1&&r.match(f.replace("www.",""))){o=c[u][1];break}}else if(i.match(f)){o=c[u][1];break}if(!o&&i.indexOf("www.")<0){for(u=0,l=s.length;u<l&&(h=new RegExp("(https://|http://|^)"+s[u]+"."),!(o=t[d=i.replace(h,"www.")]));u++);o||(o=t[d="www."+i])}}return o||"unknown-site-on-"+n};t.TRC=t.TRC||{},TRC.trk={init:function(){TRC.utm||TRC.hasTrk||t._tfa&&!t._tfa.config.hasValidConfig()||(TRC.hasTrk=!0,T()&&(v=at(u),TRC.trk.execute()))},execute:function(){F(t._taboola),U(),TRC._getGlobalRequestId=j,TRC._getItemId=H,TRC._getItemType=Q,TRC.publisherId=TRC.publisherId||v,f&&rt()}},t._tfa&&!t._tfa.config.safeGet("tfa:trk:enabled",!1)||TRC.trk.init()}(window,document,'udemy',"363-145-RELEASE",function(itemid,type,canon){if(!canon&&type=='text'&&typeof itemid=='string'&&itemid.search(new RegExp('^https?://'))==0)itemid=itemid.replace(/\?.*/,'', false);return itemid.toLowerCase();},{"host":true,"fragment":"^(/video/|!)","query":["p","id"],"truncate-at":["search.searchcompletion.com","org.mozilla.javascript.undefined"],"trailing-dirsep":true},false,function(itemurl,type,canon){return itemurl;},null,{},null,false),function(win,doc){var REFERRER_EXTRACTORS=[extractReferrerByMetaTags,extractReferrerFromTopMostReferrer,getNAReferrer];function extractReferrerByMetaTags(){for(var t=document.head.getElementsByTagName("link"),e=0;e<t.length;e++)if("referrer"===t[e].rel)return t[e].href;return null}function extractReferrerFromTopMostReferrer(){var t=getTopMostReferrer();return t?innerExtractReferrerFromTopMostReferrer(t):null}win._tfa=win._tfa||[],function(){if("function"==typeof window.CustomEvent)return!1;function t(t,e){e=e||{bubbles:!1,cancelable:!1,detail:void 0};var n=document.createEvent("CustomEvent");return n.initCustomEvent(t,e.bubbles,e.cancelable,e.detail),n}t.prototype=window.Event.prototype,window.CustomEvent=t}(),win._tfa.eventUtils=win._tfa.eventUtils||{},win._tfa.eventUtils.dispatchEvent=win._tfa.eventUtils.dispatchEvent||function(t,e){"function"==typeof CustomEvent&&document.dispatchEvent(new CustomEvent(t,{detail:e||{}}))},win._tfa.getDateNow=win._tfa.getDateNow||function(){var t,e;if(Date.now){if("number"==typeof(t=Date.now()))return t;if("number"==typeof(e=Number(t)))return e}return(new Date).getTime()},win.TRC=win.TRC||{},TRC.useStorageDetection=TRC.useStorageDetection||!0,TRC.text=TRC.text||{},TRC.text.lsplit=TRC.text.lsplit||function(t,e,n){var i=t.split(e);return i.slice(0,n-1).concat(i.length>=n?i.slice(n-1).join(e):[])},TRC.util=TRC.util||{},TRC.util.jsonParseWithNative=TRC.util.jsonParseWithNative||function(t){try{return JSON.parse(t)}catch(e){return TRC.util.jsonParseWithEval(t)}},TRC.util.jsonParseWithEval=TRC.util.jsonParseWithEval||function(text){try{return eval("("+String(text)+")")}catch(t){throw new Error("JSON parse error - invalid input!")}},TRC.util.safeAddParam=TRC.util.safeAddParam||function(t,e,n){var i,r;n&&e&&t&&(i=encodeURIComponent(t),r=encodeURIComponent(e),n.push(i+"="+r))},win.TRCImpl=win.TRCImpl||{},TRCImpl.global=TRCImpl.global||{},win.__trcError=win.__trcError||function t(){},win.__trcJSONify=win.__trcJSONify||function(t){if(window.JSON&&window.JSON.stringify&&window.TRCImpl&&window.TRCImpl.global["use-native-json-stringify"])return window.JSON.stringify(t);function e(t){return'"'+t.replace(/[\s\S]/g,function(t){switch(t){case'"':return'\\"';case"\\":return"\\\\";case"\n":return"\\n";case"\r":return"\\r"}return t})+'"'}function n(t){for(var e=[],n=0;n<t.length;n++)e[n]=__trcJSONify(t[n]);return e}function i(t){var n=[];for(var i in t)t.hasOwnProperty(i)&&n.push(e(i)+":"+__trcJSONify(t[i]));return n}return t instanceof Array?"["+n(t).join(",")+"]":t instanceof Object?"{"+i(t).join(",")+"}":null===t?"null":"string"==typeof t?e(t):void 0===t?"undefined":t.toString()},TRC.util.getReferrer=TRC.util.getReferrer||function(){for(var t,e=0;!t&&e<REFERRER_EXTRACTORS.length;e++)t=REFERRER_EXTRACTORS[e].call(this);return t};var innerExtractReferrerFromTopMostReferrer=TRC.util.getReferrer.innerExtractReferrerFromTopMostReferrer=function(t){return/https?:\/\/\w+\.taboola(?:syndication)?\.com/.test(t)?t.split("?")[0]:t.substr(0,400)};function getTopMostReferrer(){try{return decodeURI(top.window.document.referrer)}catch(t){}return null}function getNAReferrer(){return"N/A"}}(window,document),function(t,e){var n="taboola global",i="trctestcookie";function r(){for(var t="trc_cookie_storage",e=new Object,n=document.cookie.split(/;\s+/),i=0;i<n.length;i++){var r=TRC.text.lsplit(n[i],"=",2),o=unescape(r[0]),a=unescape(r[1]);if(o==t){for(var s=a.split("|"),c=0;c<s.length;c++){var r=s[c].split("=");e[unescape(r[0])]=unescape(r[1])}break}}function u(){var n=new Array,i,r;for(var o in e)e.hasOwnProperty(o)&&null!=e[o]&&(n[n.length]=escape(o)+"="+escape(e[o]));i=n.length>0?1:-1,r=new Date((new Date).getTime()+365*i*864e5),document.cookie=t+"="+escape(n.join("|"))+";path=/;expires="+r.toUTCString()}return this.getValue=function(t){return e.hasOwnProperty(t)?e[t]:null},this.setValue=function(t,n){e[t]=n,u()},this.removeKey=function(t){delete e[t],u()},this}function o(t){var e=t||{};return this.getValue=function(t){return e[t]?e[t]:null},this.setValue=function(t,n){e[t]=n},this.removeKey=function(t){delete e[t]},this.getData=function(){return e},this}function a(e){return this.getValue=function(n){return t[e+"Storage"].getItem(n)},this.setValue=function(n,i){try{t[e+"Storage"].setItem(n,i)}catch(t){}},this.removeKey=function(n){try{t[e+"Storage"].removeItem(n)}catch(t){}},this}function s(e){var n=t[e+"Storage"],i=(new Date).getTime()+"",r="_taboolaStorageDetection";try{if(n.setItem(r,i),n.getItem(r)==i)return n.removeItem(r),n}catch(t){}return null}function c(e){try{if(t.localStorage instanceof Storage&&TRC.useStorageDetection&&s(e))return new a(e)}catch(t){return null}}var u=function e(){return this.state={},this.getLocalStorageImplementation=function(e,n){if(null!=this.state.privateStorageImpl&&"strict-w3c-storage"!=e)return this.state.privateStorageImpl;var i=t.TRCImpl?t.TRCImpl.global:{};switch(e=e||(i["local-storage-usage"]?i["local-storage-usage"]:"prefer-w3c-storage")){case"strict-w3c-storage":return c("session"===n?"session":"local");case"prefer-w3c-storage":var a=c("local");if(a)return this.state.privateStorageImpl=a;case"prefer-cookies":try{if(this.canWriteCookies())return this.state.privateStorageImpl=new r}catch(t){}default:return this.state.privateStorageImpl=new o}},this.getFirstPartyCookie=function(){if(this.state.firstPartyCookie)return this.state.firstPartyCookie;var t=this.getLocalStorageImplementation();if(t instanceof r||t instanceof o)return this.state.firstPartyCookie=t;try{if(this.canWriteCookies())return this.state.firstPartyCookie=new r}catch(t){}return this.state.firstPartyCookie=new o},this.canWriteCookies=function(){var t;return document.cookie=i+"=ok",t=-1!==document.cookie.indexOf(i),document.cookie=i+"=;expires=Thu, 01 Jan 1970 00:00:01 GMT;",t},this.getDummyStorage=function(t){return new o(t)},this.getValue=function(t){return this.getPublisherValue(n,t)},this.storePublisherValue=function(t,e,n){var i;this.isNotAllowedToWriteValue(e,n)||(i=this.buildKeyWithPublisher(t,e),this.getLocalStorageImplementation().setValue(i,n),this.addKeyToStoredKeysList(i))},this.isNotAllowedToWriteValue=function(t,e){return null==e||void 0==e||TRC.doNotTrack&&!this.isAllowedKeyWhenDoNotTrack(t)},this.buildKeyWithPublisher=function(t,e){return t+":"+e},this.getPublisherValue=function(t,e){return TRC.doNotTrack&&!this.isAllowedKeyWhenDoNotTrack(e)?null:this.getLocalStorageImplementation().getValue(this.buildKeyWithPublisher(t,e))},this.addKeyToStoredKeysList=function(t){var e=this.getStoredKeysList();-1===e.indexOf(t)&&(e.push(t),this.setStoredKeysList(e))},this.getStoredKeysList=function(){var t=this.getLocalStorageImplementation().getValue(this.buildKeyWithPublisher(n,"local-storage-keys")),e;try{e=TRC.util.jsonParseWithNative(t)||[]}catch(t){e=[],__trcError("Could not parse local storage keys",t)}return e},this.setStoredKeysList=function(t){var e;try{e=__trcJSONify(t),this.getLocalStorageImplementation().setValue(this.buildKeyWithPublisher(n,"local-storage-keys"),e)}catch(t){return void __trcError("Could not stringify local storage keys",t)}},this.isAllowedKeyWhenDoNotTrack=function(e){var n,i=(t.TRCImpl&&t.TRCImpl.global||{})["dnt-allowed-keys"]||["session-id","trc_css-isolation"],r;return null!==e&&void 0!==e&&(r=e.split(":")[1]||e,-1!==i.indexOf(r))},this.storeUserId=function(t){this.isNotAllowedToWriteValue("user-id",t)||this.storePublisherValue(n,"user-id",t)},this.getUserIdFirstPartyCookie=function(){return this.getFirstPartyCookie().getValue(this.buildKeyWithPublisher(n,"user-id"))},this.getSessionDataFirstPartyCookie=function(){var t=this.getStoredKeysList();for(key in t)if(t[key].includes("session-data"))return TRC.tfaPageManager.getLocalStorageImplementation().getValue(t[key])},this.initState=function(){void 0===this.state&&(this.state={}),this.state.privateStorageImpl=null},this.initState(),this};TRC.tfaPageManager=TRC.tfaPageManager||new u}(window,document),function(t,e){var n=TRC.pageManager||TRC.tfaPageManager;function i(t,e,n){var i,r;n&&e&&t&&(i=encodeURIComponent(t),r=encodeURIComponent(e),n.push(i+"="+r))}function r(t,e){t&&e&&(e[t]=!0)}function o(t,e,n){for(var i={},o=0;o<arguments.length;o++)r(arguments[o],i);return Object.keys(i).length>1}TRC.tfaUserId={initialized:!1,userId:null,getUserId:function(){return this.userId},sendUserIdsToTrc:function(t,e,n){var i,r=[];if(o(t,e,n))return TRC.util.safeAddParam("uiref",t,r),TRC.util.safeAddParam("uils",e,r),TRC.util.safeAddParam("uifpc",n,r),(i=new Image).src="//trc.taboola.com/sg/taboola-tfa/1/um/?"+r.join("&"),i},readAndStoreUserId:function(){var t=this.extractUserIdFromReferrer(),e=n.getValue("user-id"),i=n.getUserIdFirstPartyCookie();t&&(this.sendUserIdsToTrc(t,e,i),n.storeUserId(t),i&&n.getFirstPartyCookie().setValue("taboola global:user-id",t)),this.userId=t||e||i},extractUserIdFromReferrer:function(){var t=this.getReferrer();if(t&&t.indexOf("taboola")>-1)return this.getParameterByName("ui",t)},getReferrer:function(){return e.referrer},getParameterByName:function(t,e){if(!e||!t)return null;t=t.replace(/[\[\]]/g,"\\$&");var n,i=new RegExp("[?&]"+t+"(=([^&#]*)|&|#|$)").exec(e);return i?i[2]?decodeURIComponent(i[2].replace(/\+/g," ")):"":null},init:function(){this.initialized||(this.readAndStoreUserId(),this.initialized=!0)}},TRC.tfaUserId.init()}(window,document),function(){var t=window.TRC||{},e="_tfa",n,i,r,o=6*60*60*1e3,a="eng_mt",s=27;function c(t,e,n){var i=t&&t.sessionStartTime?t.sessionStartTime+this.getSessionDuration()-e:this.getSessionDuration();i<0&&(i=0),setTimeout(function(){n(i)},i)}function u(t){return t.ver&&t.ver===this.getDataVersion()}var l=function t(){this.state={}};l.prototype={constructor:l,init:function t(e){var i=n.getDateNow(),r=this.getSessionDataFromStorage();if(this.getIsLocalStorageAvailable())return c.call(this,r,i,e),r&&r.sessionStartTime?this.state=r:(this.state={ver:s,sessionStartTime:i,scrollDepth:0,sessionDepth:[],timeOnSite:0,numOfTimesMetricsSent:0},this.persistMetricsData()),this},resetStorageMetricData:function e(){var n=t.tfaPageManager.getLocalStorageImplementation("strict-w3c-storage");this.state={},n.setValue(a,"")},getSessionDataFromState:function t(){return this.state},getSessionDataFromStorage:function e(){var n,i,o;if(n=t.tfaPageManager.getLocalStorageImplementation("strict-w3c-storage"),r=!!n,o=(i=n&&n.getValue(a))&&t.util.jsonParseWithNative&&t.util.jsonParseWithNative(i)){if(u.call(this,o)&&!this.hasSessionEnded(o))return o;this.resetStorageMetricData()}},hasSessionEnded:function t(e){var i=e?e.sessionStartTime:this.getSessionStartTime();return!i||n.getDateNow()-i>this.getSessionDuration()},persistMetricsData:function e(){var n=t.tfaPageManager.getLocalStorageImplementation("strict-w3c-storage"),i=this.state;n&&window.__trcJSONify&&n.setValue(a,window.__trcJSONify(i))},persistSpecificMetricsData:function e(n,i){var r=t.tfaPageManager.getLocalStorageImplementation("strict-w3c-storage"),o;r&&window.__trcJSONify&&n&&(o=this.getSessionDataFromStorage())&&(o[n]=i,r.setValue(a,window.__trcJSONify(o)))},updateMetricState:function t(e,n){var i=this.state;e&&(i[e]=n)},getSessionDuration:function t(){return o},getSessionStartTime:function t(){return this.state.sessionStartTime},getScrollDepth:function t(){return this.state.scrollDepth},getTimeOnSite:function t(){return this.state.timeOnSite},getNumOfTimesMetricsSent:function t(){return this.state.numOfTimesMetricsSent},getDataVersion:function(){return s},getIsLocalStorageAvailable:function(){return r}},(i=(n=window[e]=window[e]||[]).TEM=n.TEM||{}).ESU=i.ESU||new l}(),function(){var t="_tfa",e;function n(t,e){this.storageUtils.updateMetricState(t,e),this.storageUtils.persistSpecificMetricsData(t,e)}var i=function t(){};i.prototype={constructor:i,init:function t(e){this.storageUtils=e||{},this.setVisibilityProperties(),this.initMetricData(this.storageUtils),this.initVisibilityListener()},initMetricData:function t(n){this.isPageHidden=document[this.hiddenProp],this.timeOnSite=n.getTimeOnSite()||0,this.lastVisibleStartTime=this.isPageHidden?0:e.getDateNow()},initVisibilityListener:function t(){document.addEventListener(this.visibilityChangeEventName,this.handleVisibilityChange.bind(this),!1)},setVisibilityProperties:function t(){void 0!==document.hidden?(this.hiddenProp="hidden",this.visibilityChangeEventName="visibilitychange"):void 0!==document.msHidden?(this.hiddenProp="msHidden",this.visibilityChangeEventName="msvisibilitychange"):void 0!==document.webkitHidden&&(this.hiddenProp="webkitHidden",this.visibilityChangeEventName="webkitvisibilitychange")},calcTimeOnSite:function t(){return this.lastVisibleStartTime?this.timeOnSite+(e.getDateNow()-this.lastVisibleStartTime):this.timeOnSite},handleVisibilityChange:function t(){this.isPageHidden=document[this.hiddenProp],this.isPageHidden?(this.timeOnSite=this.calcTimeOnSite(),n.call(this,"timeOnSite",this.timeOnSite)):this.lastVisibleStartTime=e.getDateNow()},getTimeOnSite:function t(){return this.isPageHidden?this.timeOnSite:this.calcTimeOnSite()}},(e=window[t]=window[t]||[]).TEM=e.TEM||{},e.TEM.TOS=e.TEM.TOS||new i}(document),function(){var t="_tfa",e,n=!1,i;function r(){return void 0==document.body||void 0==document.documentElement?0:(n=!0,Math.max(document.body.scrollHeight,document.documentElement.scrollHeight,document.body.offsetHeight,document.documentElement.offsetHeight,document.body.clientHeight,document.documentElement.clientHeight))}function o(t,e){this.storageUtils.updateMetricState(t,e),this.storageUtils.persistSpecificMetricsData(t,e)}function a(t,e){var n;return function(){var i=this,r=arguments;clearTimeout(n),n=setTimeout(function(){t.apply(i,r)},e)}}var s=function t(){};s.prototype={constructor:s,init:function t(e){this.storageUtils=e||{},this.maxScrollPercentage=this.storageUtils.getScrollDepth()||0,this.initEventListeners(),this.updateMeasurements(),this.calcMaxScrollPercentage()},getScrollDepth:function t(){return n||this.calcMaxScrollPercentage(),this.maxScrollPercentage},initEventListeners:function t(){window.addEventListener("resize",a(this.onResize.bind(this),100)),window.addEventListener("scroll",a(this.onScroll.bind(this),50))},onResize:function t(){this.updateMeasurements()},onScroll:function t(){this.calcMaxScrollPercentage()},updateMeasurements:function t(){this.winHeight=window.innerHeight,this.docHeight=r()},calcMaxScrollPercentage:function t(){var e=window.pageYOffset||(document.documentElement||document.body.parentNode||document.body).scrollTop,i;n||this.updateMeasurements(),(i=0==this.docHeight?0:Math.floor((e+this.winHeight)/this.docHeight*100))>this.maxScrollPercentage&&(this.maxScrollPercentage=i,o.call(this,"scrollDepth",this.maxScrollPercentage))}},(i=(e=window[t]=window[t]||[]).TEM=e.TEM||{}).SCD=i.SCD||new s}(),function(){var t,e,n=window["_tfa"].TEM,i=function(){};i.prototype={constructor:i,init:function(t,e){this.storageUtils=t,this.refreshFromStorage(),document.addEventListener(e,this.handleUnipTfaPush.bind(this))},getKey:function(){return"ssd"},setState:function(t){this.visitedUrls={};for(var e=0;e<t.length;e++)this.visitedUrls[t[e]]=!0},getState:function(){return this.visitedUrls?Object.keys(this.visitedUrls):[]},getMetric:function(){return this.getState().length},persistState:function(){var t="sessionDepth",e=this.getState();this.storageUtils.updateMetricState(t,e),this.storageUtils.persistSpecificMetricsData(t,e)},refreshFromStorage:function(){var t=this.storageUtils.getSessionDataFromStorage(),e;t||(t=this.storageUtils.getSessionDataFromState()),(e=t["sessionDepth"])||(e=[]),this.setState(e)},handleUnipTfaPush:function(t){var e=t.detail.command,n;"event"===e.notify&&"page_view"===e.name&&(n=window.location.href,this.visitedUrls[n]||(this.visitedUrls[n]=!0,this.persistState()))}},n.SSD=n.SSD||new i}(),function(){var t="_tfa",e=window[t]=window[t]||[],n,i=e.TEM=e.TEM||{},r=i.ESU||{},o=i.SCD||{},a=i.SSD||{},s=i.TOS||{},c=1500,u="numOfTimesMetricsSent",l="pre_d_eng_tb",f={SESSION_END:"SESSION_END"},h,d;function g(t,e){var n=s.getTimeOnSite(),i=o.getScrollDepth(),c=a.getMetric();return{notify:"event",name:l,tos:n,scd:i,ssd:c,est:r.getSessionStartTime(),ver:r.getDataVersion(),isls:r.getIsLocalStorageAvailable(),src:t,invt:e}}function p(t,e){var i=g(t,e);i.est&&(n.pageViewAccountIds?m(n.pageViewAccountIds,i):v(i))}function m(t,e){var n=Object.keys(t);n.length>0?n.forEach(function(n){e.id=t[n],v(e)}):v(e)}function v(t){n.push(t)}function S(t){clearTimeout(d),i.sendMetrics("se",t),r.resetStorageMetricData()}function T(t){var e=r.getSessionDataFromStorage(),n,o;(isNaN(h)||h<0)&&(h=0),r.hasSessionEnded()||((o=e&&e.numOfTimesMetricsSent!==h)?(h=e.numOfTimesMetricsSent,r.updateMetricState(u,h)):(n=++h,r.updateMetricState(u,n),r.persistSpecificMetricsData(u,n),i.sendMetrics("i",t)),w())}function w(){var t=c*Math.pow(2,h);d=setTimeout(function(){T(t)},t)}function y(){h=r.getNumOfTimesMetricsSent()||0,w()}function R(){y()}function b(){i.initialized||(n=e.TUP||{},i.initialized=!0,i.ESU.init(S),i.ESU.getIsLocalStorageAvailable()&&(s.init(r),o.init(r),a.init(r,n.EVENTS.UNIP_TFA_PUSH),i.initSendMetricsTriggers()))}i.init=i.init||b,i.onSessionEndTrigger=i.onSessionEndTrigger||S,i.sendMetrics=i.sendMetrics||p,i.initSendMetricsTriggers=i.initSendMetricsTriggers||R,i.EVENTS=i.EVENTS||f}(),function(){var t=TRC.tfaPageManager||{},e="_tfa",n=window[e]=window[e]||[],i={event:b,subscription:E},r=/(\S+)taboola(\S+|)\.com\/libtrc\/unip\/(\S+)\/tfa\.js(\S+|)/,o=["notify","id"],a={name:"en",url:"item-url",referrer:"ref"},s="script[src$='tfa.js']",c=-1,u={defaultProtocol:"https:",host:"trc.taboola.com",httpMethod:"get",loggerEventName:"unip",logToConsole:!0},l={EMPTY_COMMAND:"EMPTY_COMMAND",MISSING_NOTIFY:"MISSING_NOTIFY",INVALID_NOTIFY:"INVALID_NOTIFY",MISSING_NAME:"MISSING_NAME",INVALID_ID:"INVALID_ID"},f={UNIP_TFA_PUSH:"UNIP_TFA_PUSH",TFA_VALIDATION_ERROR:"TFA_VALIDATION_ERROR"},h={page_view:_};function d(){var t=O();t.initialized&&t.domAccountId&&setTimeout(function(){for(var t=O().asyncQueue;t.length;)D(t.shift())},0)}function g(){var t=p(),e;if(t&&(e=t.src.replace(r,"$3")))return/^\d+$/.test(e)?parseInt(e,10):(M("Value '"+e+"' is invalid for 'id' param in script source url '"+t.src+"'. Only numeric values are allowed."),c)}function p(){for(var t=document.querySelectorAll(s),e,n=0;n<t.length;n++)if((e=t[n]).src.indexOf("/unip/")>0)return e}function m(){return n.getDateNow()}function v(t){t["ce"]="subscr"}function S(e){var n=t.getSessionDataFirstPartyCookie();void 0!==n&&n&&(e["sd"]=n)}function T(t){try{TRC.tfaUserId.getUserId()&&(t["ui"]=TRC.tfaUserId.getUserId())}catch(e){M("Error while trying to add user-id param, params="+JSON.stringify(t),e)}}function w(t){var e=O();e.referrer||(e.referrer=TRC.util.getReferrer()),t[a.referrer]=e.referrer}function y(t){var e={},n=!1,i;for(var r in t.tim=m(),t)t.hasOwnProperty(r)&&o.indexOf(r)<0&&(e[i=a.hasOwnProperty(r)?a[r]:r]=t[r],n=!0);return T(e),w(e),n&&e}function R(t,e){var n=(window.location.protocol||u.defaultProtocol)+"//"+u.host;try{TRC.TRCLogger[u.httpMethod](n,u.loggerEventName,e,t)}catch(n){M("Error while trying to send to server event with id '"+t+"' and params '"+JSON.stringify(e)+"'.",n)}}function b(t){var e=O(),n=t&&t.id||e.domAccountId,i,r;n?n!==c&&(i=y(t),n=parseInt(n,10),r=C(i),h[r]&&h[r](i,n),R(n,i)):e.asyncQueue.push(t)}function C(t){return t[a.name]}function I(t,e){void 0!==t["sourceurl"]&&t["sourceurl"]&&(e["surl"]=t["sourceurl"])}function E(t){var e=O(),n=t&&t.id||e.domAccountId;if(n){if(n!==c){var i=y(t);v(i),S(i),I(t,i),R(parseInt(n,10),i)}}else e.asyncQueue.push(t)}function _(t,e){var n=O();e&&n.pageViewAccountIds&&(n.pageViewAccountIds[e]=parseInt(e,10))}function N(t){return t?t.notify?i.hasOwnProperty(t.notify)?t.name?!(t.hasOwnProperty("id")&&!/^\d+$/.test(t.id))||(P(l.INVALID_ID,t,"Value '"+t.id+"' is invalid for 'id' field in command '"+JSON.stringify(t)+"'. Only numeric values are allowed."),!1):(P(l.MISSING_NAME,t,"Mandatory 'name' field is missing in command '"+JSON.stringify(t)+"'."),!1):(P(l.INVALID_NOTIFY,t,"Value '"+t.notify+"' is invalid for 'notify' field in command '"+JSON.stringify(t)+"'."),!1):(P(l.MISSING_NOTIFY,t,"Mandatory 'notify' field is missing in command '"+JSON.stringify(t)+"'."),!1):(P(l.EMPTY_COMMAND,t,"Command is '"+t+"'."),!1)}function O(){return window&&window[e]&&window[e].TUP||{}}function D(t){var e=O();if(N(t))try{i[t.notify](t),n.eventUtils.dispatchEvent(f.UNIP_TFA_PUSH,{accountId:e.domAccountId,command:t})}catch(e){M("An error occurred while handling command '"+JSON.stringify(t)+"'.",e)}}function P(t,e,i){var r=O();n.eventUtils.dispatchEvent(f.TFA_VALIDATION_ERROR,{accountId:r.domAccountId,errorCode:t,command:e}),M(i)}function M(t,e){u.logToConsole&&k(t,e)}function k(t,e){e?console.log("Taboola Pixel: "+t,e):console.log("Taboola Pixel: "+t)}function U(){var t=n.TUP=n.TUP||{},e=n.TEM||{};t.domAccountId=t.domAccountId||g(),window.TRC=window.TRC||{},t.initialized||(t.push=n.TUP.push||D,t.initialized=!0,t.asyncQueue=[],t.EVENTS=f,t.pageViewAccountIds={},e&&e.init&&e.init()),d()}U()}(),function(t,e){var n="_tfa",i={orderid:"orderid",currency:"currency",revenue:"revenue",quantity:"quantity",name:"name",attributionGroup:"attributionGroup"},r={type:"marking-type"},o=(t.location.protocol.match(/http/)?t.location.protocol:"http:")+"//trc.taboola.com/{$publishreId}log/3/{$logType}?",a=/(\S+)taboola(\S+|)\.com\/libtrc\/(\S+)\/tfa\.js(\S+|)/,s="unip/",c=[],u=[],l="http:";function f(t){var e;switch(t.notify){case"action":e=c;break;case"mark":e=u;break;case"event":case"subscription":e=queue.TUP;break;default:return}e&&e.push(t)}function h(){return TRC&&TRC.tfaUserId&&TRC.tfaUserId.getUserId()?"&ui="+encodeURIComponent(TRC.tfaUserId.getUserId()):""}function d(){var t,e,n=y();if(n)for(t=0,e=c.length;t<e;t++)m(p(o,{$publishreId:n?n+"/":"",$logType:"action"})+"tim="+escape(S())+"&item-url="+escape(v())+R(i,c.shift())+T()+h())}function g(){var t,e,n=y();if(n)for(t=0,e=u.length;t<e;t++)m(p(o,{$publishreId:n?n+"/":"",$logType:"mark"})+"tim="+escape(S())+"&item-url="+escape(v())+R(r,u.shift())+T()+h())}function p(t,e){return t.replace(/\{([^{}]*)\}/g,function(t,n){var i=e[n];return"string"==typeof i||"number"==typeof i?i:t})}function m(t){var e;(new Image).src=t}function v(){return t.location.href}function S(){var t=new Date,e=t.getHours(),n=t.getMinutes(),i=t.getSeconds()+t.getMilliseconds()/1e3;return(e<10?"0":"")+e+":"+(n<10?"0":"")+n+":"+(i<10?"0":"")+i.toFixed(3)}function T(){var n=t.location.search,i=e.referrer.match(/(\?\S+)$/g),r="";return r=w(n.replace(/^\?/,"").split(/&/))+(i?w(i[0].replace(/^\?/,"").split(/&/)):"")}function w(t){var e="",n,i,r="trc_";for(n=0,i=t.length;n<i;n++)0==t[n].indexOf(r)&&(e=e+"&"+t[n]);return e}function y(){var t=document.getElementsByTagName("script"),e,n,i="",r;for(e=0,n=t.length;e<n;e++)if(i=(r=t[e].src).replace(a,"$3"),t[e].src&&i!==t[e].src&&i.indexOf(s)<0)return i;return i}function R(t,e){var n,i="";for(n in t)void 0!==e[n]&&(i+="&"+t[n]+"="+e[n]);return i}function b(t){for(var e=0;e<arguments.length;e++)(t=arguments[e])instanceof Object&&f(t);return C(),arguments.length}function C(){d(),g()}function I(){for(;queue.length;)b(queue.shift())}function E(){queue=t[n]=t[n]||[],queue.registered||(queue.push=b,queue.registered=!0,I())}E()}(window,document);