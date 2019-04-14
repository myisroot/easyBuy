// JavaScript Document


function addUpdate(jia) {
    var c = jia.parent().find(".n_ipt").val();
    c = parseInt(c) + 1;
    jia.parent().find(".n_ipt").val(c);
}

function jianUpdate(jian) {
    var c = jian.parent().find(".n_ipt").val();
    if (c == 1) {
        c = 1;
    } else {
        c = parseInt(c) - 1;
        jian.parent().find(".n_ipt").val(c);
    }
}


function addUpdate1(jia, p_id, p_stock) {
    var c = jia.parent().find(".car_ipt").val();
    c = parseInt(c) + 1;
    if (c > p_stock) {
        alert('商品数量不足');
        return;
    }
    jia.parent().find(".car_ipt").val(c);
    getCart(c, p_id);
}

function jianUpdate1(jian, p_id) {
    var c = jian.parent().find(".car_ipt").val();
    if (c == 1) {
        c = 1;
    } else {
        c = parseInt(c) - 1;
        jian.parent().find(".car_ipt").val(c);
        getCart(c, p_id);
    }
}

function getCart(c, p_id) {
    $.ajax({
        "url": path + "/ShoppingCat",
        "type": "POST",
        "data": {
            "action": "changeQuantity",
            "p_id": p_id,
            "quantity": c
        },
        "dataType": "JSON",
        "success": function (data) {
            console.log(data)
            if (data.status == 1) {
                $('#cart').load(path + '/shop/cart.jsp');
            } else {
                alert(data.message);
            }
        }
    });
}

function DelItem() {
    CloseDiv('MyDiv', 'fade');
    $.ajax({
        "url": path + "/ShoppingCat",
        "type": "POST",
        "data": {
            "action": "delItem",
            "p_id": pid,
        },
        "dataType": "JSON",
        "success": function (data) {
            if (data.status == 1) {
                $('#cart').load(path + '/shop/cart.jsp');
            } else {
                alert(data.message);
            }
        }
    });
}