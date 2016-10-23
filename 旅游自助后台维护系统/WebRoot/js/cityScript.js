var citySelector = {};

citySelector.pc = new Array();

citySelector.pc[0] = new Array("北京", "北京|东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");
citySelector.pc[1] = new Array("上海", "上海|黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");
citySelector.pc[2] = new Array("天津", "和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
citySelector.pc[3] = new Array("重庆", "万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁|大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
citySelector.pc[4] = new Array("河北", "石家庄|邯郸|邢台|保定|张家口|承德|廊坊|唐山|秦皇岛|沧州|衡水");
citySelector.pc[5] = new Array("山西", "太原|大同|阳泉|长治|晋城|朔州|吕梁|忻州|晋中|临汾|运城");
citySelector.pc[6] = new Array("内蒙古", "呼和浩特|包头|乌海|赤峰|呼伦贝尔|通辽|乌兰察布|鄂尔多斯|巴彦淖尔");
citySelector.pc[7] = new Array("辽宁", "沈阳|大连|鞍山|抚顺|本溪|丹东|锦州|营口|阜新|辽阳|盘锦|铁岭|朝阳|葫芦岛");
citySelector.pc[8] = new Array("吉林", "长春|吉林|四平|辽源|通化|白山|松原|白城");
citySelector.pc[9] = new Array("黑龙江", "哈尔滨|齐齐哈尔|牡丹江|佳木斯|大庆|绥化|鹤岗|鸡西|黑河|双鸭山|伊春|七台河");
citySelector.pc[10] = new Array("江苏", "南京|镇江|苏州|南通|扬州|盐城|徐州|连云港|常州|无锡|宿迁|泰州|淮安");
citySelector.pc[11] = new Array("浙江", "杭州|宁波|温州|嘉兴|湖州|绍兴|金华|衢州|舟山|台州|丽水");
citySelector.pc[12] = new Array("安徽", "合肥|芜湖|蚌埠|马鞍山|淮北|铜陵|安庆|黄山|滁州|宿州|池州|淮南|巢湖|阜阳|六安|宣城|亳州");
citySelector.pc[13] = new Array("福建", "福州|厦门|莆田|三明|泉州|漳州|南平|龙岩|宁德");
citySelector.pc[14] = new Array("江西", "南昌市|景德镇|九江|鹰潭|萍乡|新馀|赣州|吉安|宜春|抚州|上饶");
citySelector.pc[15] = new Array("山东", "济南|青岛|淄博|枣庄|东营|烟台|潍坊|济宁|泰安|威海|日照|莱芜|临沂|德州|聊城|滨州|菏泽");
citySelector.pc[16] = new Array("河南", "郑州|开封|洛阳|平顶山|安阳|鹤壁|新乡|焦作|濮阳|许昌|漯河|三门峡|南阳|商丘|信阳|周口|驻马店|济源");
citySelector.pc[17] = new Array("湖北", "武汉|宜昌|荆州|黄石|荆门|黄冈|十堰|随州|咸宁|孝感|鄂州|襄阳");
citySelector.pc[18] = new Array("湖南", "长沙|常德|株洲|湘潭|衡阳|岳阳|邵阳|益阳|娄底|怀化|郴州|永州|张家界");
citySelector.pc[19] = new Array("广东", "广州|深圳|珠海|汕头|东莞|中山|佛山|韶关|江门|湛江|茂名|肇庆|惠州|梅州|汕尾|河源|阳江|清远|潮州|揭阳|云浮");
citySelector.pc[20] = new Array("广西", "南宁|柳州|桂林|梧州|北海|崇左|来宾|防城港|钦州|贵港|玉林|贺州|百色|河池");
citySelector.pc[21] = new Array("海南", "海口|三亚|三沙");
citySelector.pc[22] = new Array("四川", "成都|绵阳|自贡|攀枝花|泸州|德阳|广元|遂宁|内江|乐山|资阳|宜宾|南充|达川|雅安|巴中|广安|眉山");
citySelector.pc[23] = new Array("贵州", "贵阳|六盘水|遵义|安顺|铜仁|毕节");
citySelector.pc[24] = new Array("云南", "昆明|昭通|曲靖|玉溪|普洱|保山|丽江|临沧");
citySelector.pc[25] = new Array("西藏", "拉萨");
citySelector.pc[26] = new Array("陕西", "西安|铜川|宝鸡|咸阳|渭南|汉中|安康|商洛|延安|榆林");
citySelector.pc[27] = new Array("甘肃", "兰州|嘉峪关|金昌|白银|天水|酒泉|张掖|武威|定西|陇南|平凉|庆阳");
citySelector.pc[28] = new Array("宁夏", "银川|石嘴山|吴忠|固原|中卫");
citySelector.pc[29] = new Array("青海", "西宁|海东");
citySelector.pc[30] = new Array("新疆", "乌鲁木齐|克拉玛依");
citySelector.pc[31] = new Array("其它", "澳门|香港|台湾");

citySelector.hotCity = ["北京", "上海", "南宁", "桂林"];

citySelector.hotCityhtmls = "";
citySelector.provHtmls = "";
for (var j = 0; j < citySelector.pc.length; j++) {
    citySelector.provHtmls += "<li data-xuhao='" + j + "'><span class='provinceName'>" + citySelector.pc[j][0] + "</span></li>";
}
for (var i = 0; i < citySelector.hotCity.length; i++) {
    citySelector.hotCityhtmls += "<li class='js_cityName'>" + citySelector.hotCity[i] + "</li>";
}

citySelector.template = '<div class="city-box" id="js_cityBox"><div class="prov-city" id="js_provCity"><p>热门城市</p><ul>' + citySelector.hotCityhtmls + '</ul></div><div class="provence"><div>选择省份</div><div><ul id="js_provList">' + citySelector.provHtmls + '</ul></div></div></div>';

citySelector.cityInit = function (input) {

    $("#" + input).click(function () {

        $("#js_cityBox").remove();
        $("body").append(citySelector.template);

        var _top = $(this).offset().top + 40,
            _left = $(this).offset().left,
            _width = $(window).width();
        if (_width - _left < 450) {
            $("#js_cityBox").css({ "top": _top + "px", "right": "0px" }).addClass("rightSelector");
        }
        else {
            $("#js_cityBox").css({ "top": _top + "px", "left": _left + "px" });
        }

        var label = "false";
        $("#js_provList").on("click", ".provinceName", function () {
            function createUl(_this){
                _this.css({ "background": "#fff", "border-color": "#d8d8d8", "border-bottom-color": "#fff", "position": "absolute", "top": "0", "left": "0", "z-index": "999999" });
                var xuhao = _this.parent("li").attr("data-xuhao"),
                    cityArr = citySelector.pc[xuhao][1].split("|"),
                    cityHtmls = "<ul id='js_provCitys'>";
                for (var i = 0; i < cityArr.length; i++) {
                    cityHtmls += "<li class='js_cityName'>" + cityArr[i] + "</li>";
                }
                cityHtmls += "</ul>";
                $("#js_provCitys").remove();
                _this.parent("li").append(cityHtmls);
            }

            if (label == "false") {
                label = "true";
                $(this).attr("now-item", "true");
                createUl($(this));
            }
            else {
                if ($(this).attr("now-item") == "" || $(this).attr("now-item") == "false" || $(this).attr("now-item") == undefined) {
                    $(this).parents("#js_provList").find("span").attr("now-item", "false");
                    $(this).attr("now-item", "true");
                    $("#js_provList span").css({ "background": "", "border-color": "", "border-bottom-color": "", "position": "", "top": "", "left": "", "z-index": "" });
                    $("#js_provCitys").remove();
                    createUl($(this));
                }
                else {
                    label = "false";
                    $(this).css({ "background": "", "border-color": "", "border-bottom-color": "", "position": "", "top": "", "left": "", "z-index": "" });
                    $("#js_provCitys").remove();
                }
            }

        });

        var _input = input;
        $("#js_cityBox").on("click", ".js_cityName", function (e) {
            e.stopPropagation();
            $("#" + _input).val($(this).html());
            $("#js_cityBox").remove();
        });
    });
}