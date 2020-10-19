var key_attr = new Array();
var val_attr = new Array();

FusionCharts.addEventListener('rendered', function (e, d) {
    for (var x in e) {
        key_attr.push(x);
        val_attr.push(e[x]);
        console.log(x, ': ', e[x]);
    }

    key_attr.push("chartType");
    val_attr.push(e.sender.chartType());

    key_attr.push("renderer");
    val_attr.push(e.data.renderer);

    document.body.style.backgroundColor = '#00ff00'
});

FusionCharts.addEventListener('bI', function (e, d) {
    for (var x in e) {
        key_attr.push(x);
        val_attr.push(e[x]);
        console.log(x, ': ', e[x]);
    }

    key_attr.push("chartType");
    val_attr.push(e.sender.chartType());

    key_attr.push("renderer");
    val_attr.push(e.data.renderer);

    document.body.style.backgroundColor = '#00ff00'
});