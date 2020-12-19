var stomp = null;

// подключаемся к серверу по окончании загрузки страницы
window.onload = function() {
    connect();
};

// хук на интерфейс
$(function () {
    $("products-form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#addToBucket").click(function() { sendContent(); });
});
// отправка сообщения на сервер
function sendContent() {
    stomp.send("/app/auth/profile/bucket", {}, JSON.stringify({
        'title': $("#title").val(),
        'price': $("#price").val()
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
    $("#bucket-table").append("<tr>" +
        "<td>" +product.title +"</td>" +
        "<td>" +product.price +"</td>" +
        "<td>" + 1 + "</td>" +
        "<td>" + product.price+ "</td>" +
        "</tr>");
}