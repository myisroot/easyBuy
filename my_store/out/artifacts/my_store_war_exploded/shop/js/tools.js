{
  /* 
  getRandom(lower, upper);获取范围内的随机数
  getCode(); 返回一个四位的验证码
  getTime(); 返回当前系统时间
  myChildrn(elem); 返回元素的所有子节点
  insertAfter(targetNodes 标志的节点, afterNodes要插入的节点 ); 在节点后面插入节点  
  getScroll();返回滚动条的距离  left top
  getViewSize();返回可视化窗口Width Height
  getStyle(elem 节点, prop属性);返回要查询节点的样式
  formatNumber(str 数字字符串);  返回每隔3个加一个,的数字字符串
  IsNull(elem); 验证是否是空
  IsDate(elem);//判断日期类型是否为YYYY-MM-DD格式的类型 
  */

  /* getComputedStyle() 以数组返回 查看css属性
  ie独有  currentStyle 属性   Dom元素.currentStyle.css属性
  style 属性   Dom元素.style.css属性
  改变滚动条位置
  scrollBy
  scrollTo
  获取父节点
  elem  Dom元素
  elem.parentElement;
  Dom.children  获取所有子元素标签
  Dom.childNodes  获取所有子元素标签包括文本标签注释标签
  Dom.offsetWidth  Dom.offsetHeigth 求元素的宽和高 包括内容 内边距 边框  只读
  Dom.offsetTop 元素距离父级的top   Dom.offsetLeft 元素距离父级的left
  Dom.offsetParent 返回带有定位的父级元素
   */
  //产生范围内的随机数
  function getRandom(lower, upper) {
    (lower = Number(lower)), (upper = Number(upper));
    if (isNaN(lower) || isNaN(upper)) {
      return;
    }
    if (lower > upper) {
      var temp = lower;
      lower = upper;
      upper = temp;
    }
    return Math.floor(Math.random() * (upper - lower + 1)) + lower;
  }

  //获取验证码
  function getCode() {
    var codeStr =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    var str = "";
    for (var i = 0; i < 4; i++) {
      var num = getRandom(0, 61);
      str += codeStr.charAt(num);
    }
    return str;
  }


  //获取系统时间
  function getTime() {
    var today = new Date();
    var y = today.getFullYear();
    var m = today.getMonth();
    var day = today.getDate();
    var h = today.getHours();
    var mi = today.getMinutes();
    var me = today.getMilliseconds();
    return y + "-" + (m + 1) + "-" + day + "-" + " " + h + ":" + mi;
  }

  //获取元素的第n个父元素
  function retParent(elem, n) {
    while (elem && n) {
      elem = elem.parentElement;
      n--;
    }
    return elem;
  }

  //获取元素的所有子元素  标签元素
  function myChildrn(elem) {
    //获取所有子元素
    var child = elem.childNodes;
    //长度
    var len = child.length;
    var arr = [];
    for (var i = 0; i < len; i++) {
      //判断是否是标签元素
      if (child[i].nodeType == 1) {
        arr.push(child[i]);
      }
    }
    return arr;
  }

  //输入正数时返回下一个元素标签，负数时返回上一个元素标签
  function retSibling(elem, n) {
    while (elem && n) {
      if (n > 0) {
        //查询下一个出现的标签元素
        elem = elem.nextElementSibling;
        n--;
      } else {
        //查询上一个标签元素
        elem = elem.previousElementSibling;
        n++;
      }
    }
    return elem;
  }



  //在什么标签后面插入哪个标签
  Element.prototype.insertAfter = function (targetNodes, afterNodes) {
    //拿出元素的下一个元素
    var nextNode = afterNodes.nextElementSibling;
    if (nextNode == null) {
      this.appendChild(afterNodes);
    } else {
      this.insertBefore(targetNodes, nextNode);
    }
  };

  //获取滚动条
  //window.pageXOffset
  //window.pageYOffset

  //获取滚动条的距离
  function getScroll() {
    if (window.pageXOffset) {
      return {
        //获取滚动条的x
        left: window.pageXOffset,
        //获取滚动条y
        top: window.pageYOffset
      };
    } else {
      return {
        //解决版本兼容性
        left: document.body.scrollLeft + document.documentElement.scrollLeft,
        top: document.body.scrollTop + document.documentElement.scrollTop
      };
    }
  }

  //获取可视化窗口大小
  //window.innerWidth,
  //document.body.clientHeight
  //获取可视化窗口大小
  function getViewSize() {
    if (window.innerWidth) {
      return {
        Width: window.innerWidth,
        Height: window.innerHeight
      };
    } else {
      if (document.compatMode == "BackCompat") {
        return {
          Width: document.body.clientWidth,
          Height: document.body.clientHeight
        };
      } else {
        return {
          Width: document.documentElement.clientWidth,
          Height: document.documentElement.clientHeight
        };
      }
    }
  }


  //getComputedStyle 查看css样式
  //改变滚动条位置
  //scrollBy
  //scrollTo
  //解决版本兼容性的获取Dom元素的css属性方法
  function getStyle(elem, prop) {
    if (window.getComputedStyle) {
      return getComputedStyle(elem, null)[prop];
    } else {
      return elem.currentStyle[prop];
    }
  }

  /* 
  移动动画
  obj[object]
  target[number]
  speed[number]
  */
  function constant(obj, target, speed) {
    // 1. 清除定时器
    clearInterval(obj.timer);
    // 2. 判断方向
    var dir = obj.offsetLeft < target ? speed : -speed;
    // 3. 设置定时器
    obj.timer = setInterval(function () {
      obj.style.left = obj.offsetLeft + dir + "px";

      if (Math.abs(target - obj.offsetLeft) < Math.abs(dir)) {
        clearInterval(obj.timer);
        obj.style.left = target + "px";
      }
    }, 20);
  }



  //加载script
  function loadScript(url, callback) {
    var script = document.createElement('script');
    script.type = 'text/javascript';
    if (script.readyState) {
      script.onreadystatechange = function () {
        if (script.readyState == 'complete' || script.readyState == 'loaded') {
          callback();
        }
      }
    } else {
      script.onload = function () {
        callback();
      }
    }
    script.src = url;
    document.head.appendChild(script);
  }

}


//格式化数字字符串
function formatNumber(strNum) {
  return strNum.replace(/(?=(\B)(\d{3})+$)/g, ',');
}


//判断是否为空
function IsNull(elem) {
  if (elem.val().trim().length != 0) {
    return false;
  }
  return true;
}






//判断日期类型是否为YYYY-MM-DD格式的类型    
function IsDate(elem) {
  if (elem.val().trim().length != 0) {
    var str = elem.val().trim();
  } else {
    return false;
  }
  if (str.match(/((19\d{2})|(200\d)|(201[0-9]))(-|\/)((0?[1-9])|(1[0-2]))(-|\/)((0?[1-9])|([1-2]\d)|(3[0-1]))$/g) != null) {
    return true;
  }
  return false;
}

//判断日期类型是否为YYYY-MM-DD hh:mm:ss格式的类型    
function IsDateTime(elem) {
  var str = elem.val().trim();
  if (str.length != 0) {
    if (str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})/) != null) {
      return true;
    }
  }
  return false;
}



function isCards(elem) {
  var str = elem.val().trim();
  if (str.length != 0) {
    if (/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test(str)) {
      return true;
    }
  }
  return false;
}


//判断输入的字符是否为:a-z,A-Z,0-9    
function IsString(elem) {
  var str = elem.val().trim();
  if (str.length != 0) {
    if (/^[a-zA-Z0-9_]+$/.test(str)) {
      return true;
    }
  }
  return false;
}

function isPoneAvailable(elem) {
  var str = elem.val().trim();
  if (str.length != 0) {
    if (/^[1][3,4,5,7,8][0-9]{9}$/.test(str)) {
      return true;
    }
  }
  return false;
}


//判断输入的EMAIL格式是否正确    
function IsEmail(elem) {
  var str = elem.val().trim();
  if (str.length != 0) {
    reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    if (reg.test(str)) {
      return true;
    }
  }
  return false;
}