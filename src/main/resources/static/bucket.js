var stomp = null;

// подключаемся к серверу по окончании загрузки страницы
window.onload = function() {
    connect();
};

// отправка сообщения на сервер
function sendContent() {
    var table = document.getElementById("products-table");
    var button = document.activeElement;
    var cellOfButton = button.parentNode;
    var row = cellOfButton.parentNode;
    var name = row.cells[0].innerText;
    var price = row.cells[1].innerText;
    var count = row.cells[2].innerText;
    stomp.send("/app/auth/profile/bucket", {}, JSON.stringify({
        'name': name,
        'price': price,
        'count': count
    }));
}

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stomp.subscribe('/topic/auth/profile/bucket', function (bucket) {
            renderItem(bucket);
        });
    });
}

function renderItem(productJson) {
    var product = JSON.parse(productJson.body);
        var num = parseFloat(document.getElementById('total-price').innerText);
        var new_num = parseFloat(num + product.price).toFixed(2);
        document.getElementById('total-price').innerText = new_num;

        var table = document.getElementById("bucket-table");
        var rows = table.getElementsByClassName('rower');
        var isNew = true;
        for (var i = 0; rows.length > i; i++) {
            if (rows[i].cells[0].innerText === product.name) {
                rows[i].cells[2].innerText = parseInt(rows[i].cells[2].innerText)+1;
                isNew = false;
                break;
            }
        }
        if (isNew === true) {
            $("#bucket-table").append("<tr>" +
                "<td>" +product.name +"</td>" +
                "<td>" +product.price.toFixed(2) +"</td>" +
                "<td>" + 1 + "</td>" +
                "<td>" + product.price.toFixed(2)+ "</td>" +
                "</tr>");
        }

}