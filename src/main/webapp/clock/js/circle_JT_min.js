/* 
 *auther:wjt
 *url:https://github.com/wjtGitHub/circle_JT
 * */
(function($) {
    var startRadian = -0.5 * Math.PI;
    var cancvsObj = "";
    var endRadian = "";
    var circleObj = "";

    function DrawClock(circle) {
        var X = 0;
        cancvsObj = document.getElementById("Circle_JT_cancvsDom");
        circleObj = cancvsObj.getContext('2d');
        window.setInterval(function() {
            var myDate = new Date();
            X = myDate.getSeconds() * 2 / 60;
            circleObj.clearRect(0, 0, circle.radius * 3, circle.radius * 3);
            if (circle.pbWidth == undefined) {
                circleObj.lineWidth = 5
            } else {
                circleObj.lineWidth = circle.pbWidth
            }
            circleObj.lineCap = 'round';
            circleObj.strokeStyle = "#cccccc";
            circleObj.beginPath();
            circleObj.arc(circle.radius + 10, circle.radius + 10, circle.radius, 0, 2 * Math.PI, false);
            circleObj.stroke();
            if (X == 0.5) {
                endRadian = 0
            } else {
                endRadian = (X - 0.5) * Math.PI
            } if (circle.pbWidth == undefined) {
                circleObj.lineWidth = 5
            } else {
                circleObj.lineWidth = circle.pbWidth
            }
            circleObj.lineCap = 'round';
            circleObj.strokeStyle = circle.pbColor;
            circleObj.beginPath();
            circleObj.arc(circle.radius + 10, circle.radius + 10, circle.radius, startRadian, endRadian, false);
            circleObj.stroke()
        }, 1000);
        return "ok"
    }

    function Drawing(circle) {
        var X = hanleData(circle);
        if (X == "error") {
            return "error"
        } else {
            if (X == 0.5) {
                endRadian = 0
            } else {
                endRadian = (X - 0.5) * Math.PI
            }
        }
        cancvsObj = document.getElementById("Circle_JT_cancvsDom");
        circleObj = cancvsObj.getContext('2d');
        if (circle.pbWidth == undefined) {
            circleObj.lineWidth = 5
        } else {
            circleObj.lineWidth = circle.pbWidth
        }
        circleObj.lineCap = 'round';
        circleObj.strokeStyle = "#cccccc";
        circleObj.beginPath();
        circleObj.arc(circle.radius + 10, circle.radius + 10, circle.radius, 0, 2 * Math.PI, false);
        circleObj.stroke();
        if (circle.pbWidth == undefined) {
            circleObj.lineWidth = 5
        } else {
            circleObj.lineWidth = circle.pbWidth
        }
        circleObj.lineCap = 'round';
        circleObj.strokeStyle = circle.pbColor;
        circleObj.beginPath();
        circleObj.arc(circle.radius + 10, circle.radius + 10, circle.radius, startRadian, endRadian, false);
        circleObj.stroke();
        return "ok"
    }

    function hanleData(circle) {
        var result = "";
        if (circle.value != "" || circle.value == 0) {
            if (circle.totalValue != undefined && circle.totalValue != "") {
                result = circle.value * 2 / circle.totalValue
            } else {
                result = circle.value * 2 / 100
            }
            return result
        } else {
            console.error("请输入目前的进度数值");
            return "error"
        }
    }

    function handleClockData(circle) {
        var result = "";
        return result
    }

    function createElementFun(circle) {
        if (circle.percentage == true) {
            var innerDom = document.createElement("div");
            var side = Math.sqrt(circle.radius * circle.radius + circle.radius * circle.radius);
            var horn = (circle.radius + 10 - side / 2) + circle.pbWidth / 2;
            innerDom.setAttribute("id", "Circle_JT_innerDom");
            innerDom.setAttribute("class", "Circle_JT_innerDom");
            innerDom.style.position = "absolute";
            innerDom.style.width = side - circle.pbWidth + "px";
            innerDom.style.height = side - circle.pbWidth + "px";
            innerDom.style.left = horn + "px";
            innerDom.style.top = horn + "px";
            innerDom.style.zIndex = 999;
            innerDom.style.textAlign = 'center';
            innerDom.style.lineHeight = side - 10 + "px";
            innerDom.style.color = circle.pbColor;
            innerDom.style.fontSize = circle.fontSize + "px";
            if (circle.totalValue == undefined || circle.totalValue == "") {
                innerDom.innerHTML = circle.value / 100 + "%"
            } else {
                innerDom.innerHTML = circle.value / circle.totalValue + "%"
            }
        }
        if (circle.digitalWatch == true && circle.clock == true) {
            var innerClockDom = document.createElement("div");
            var side = Math.sqrt(circle.radius * circle.radius + circle.radius * circle.radius);
            var horn = (circle.radius + 10 - side / 2) + circle.pbWidth / 2;
            innerClockDom.setAttribute("id", "Circle_JT_innerDom");
            innerClockDom.setAttribute("class", "Circle_JT_innerDom");
            innerClockDom.style.position = "absolute";
            innerClockDom.style.width = side - circle.pbWidth + "px";
            innerClockDom.style.height = side - circle.pbWidth + "px";
            innerClockDom.style.left = horn + "px";
            innerClockDom.style.top = horn + "px";
            innerClockDom.style.zIndex = 999;
            innerClockDom.style.textAlign = 'center';
            innerClockDom.style.lineHeight = side - 10 + "px";
            innerClockDom.style.color = circle.pbColor;
            innerClockDom.style.fontSize = circle.fontSize + "px";
            window.setInterval(function() {
                var myDate = new Date();
                var second = "";
                var minute = "";
                if (myDate.getSeconds().toString().length == 1) {
                    second = "0" + myDate.getSeconds().toString()
                } else {
                    second = myDate.getSeconds()
                } if (myDate.getMinutes().toString().length == 1) {
                    minute = "0" + myDate.getMinutes().toString()
                } else {
                    minute = myDate.getMinutes()
                }
                innerClockDom.innerHTML = myDate.getHours() + ":" + minute + ":" + second
            }, 1000)
        }
        var cancvsDom = document.createElement("canvas");
        cancvsDom.setAttribute("id", "Circle_JT_cancvsDom");
        cancvsDom.setAttribute("class", "Circle_JT_cancvsDom");
        cancvsDom.setAttribute("height", circle.radius * 2 + 20);
        cancvsDom.setAttribute("width", circle.radius * 2 + 20);
        var firstDom = document.createElement("div");
        firstDom.setAttribute("id", "Circle_JT_firstDom");
        firstDom.setAttribute("height", circle.radius * 2 + 20);
        firstDom.setAttribute("width", circle.radius * 2 + 20);
        firstDom.style.position = "relative";
        firstDom.appendChild(cancvsDom);
        if (circle.percentage == true && circle.clock != true) {
            firstDom.appendChild(innerDom)
        }
        if (circle.digitalWatch == true && circle.clock == true) {
            firstDom.appendChild(innerClockDom)
        }
        var ZeroDom = document.getElementById(circle.domId);
        ZeroDom.appendChild(firstDom)
    }
    $.extend({
        circleJt: function(circle) {
            if (circle.clock != undefined && circle.clock == true) {
                createElementFun(circle);
                if (DrawClock(circle) == "error") {
                    return
                }
            } else {
                if (circle.domId == undefined || circle.domId == "") {
                    console.error("请输入id");
                    return
                } else if (circle.radius == undefined || circle.radius == "") {
                    console.error("请输入进度条的颜色");
                    return
                } else if (circle.pbColor == undefined || circle.pbColor == "") {
                    console.error("请输入进度条的颜色");
                    return
                } else if (circle.pbWidth == "") {
                    console.error("请输入进度条的宽度");
                    return
                } else if (circle.value == undefined) {
                    console.error("缺少必要的属性--当前的进度数值");
                    return
                }
                createElementFun(circle);
                if (Drawing(circle) == "error") {
                    return
                }
            }
        }
    })
})(jQuery);