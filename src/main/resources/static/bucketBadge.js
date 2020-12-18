var stomp = null;

// подключаемся к серверу по окончании загрузки страницы
window.onload = function() {
    connect();
};

function connect() {
    var socket = new SockJS('/socket');
    stomp = Stomp.over(socket);
    stomp.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stomp.subscribe('/topic/bucket', function (bucket) {
            renderItem(bucket);
        });
    });
}

// хук на интерфейс
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#addToBucket" ).click(function() { sendContent(); });
});
// отправка сообщения на сервер
function sendContent() {
    stomp.send("/app/bucket", {}, JSON.stringify({
        'title': $("#title").val(),
        'price': $("#price").val()
    }));
}

// рендер сообщения, полученного от сервера
function renderItem(bucketSize) {
    $("#bucket-badge").value(bucketSize)
}