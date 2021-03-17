var time = function () {
    $("#content").html('');
    $("#content").append(showtime());
    const container = createBox(['user', 'position', 'code', 'news', 'rank']);
    $("#content").append(container);
    fn();
};
time();
var timer = setInterval(time, 300000);
function fn() {
    $.get("http://127.0.0.1:8080/users/0", function (res) {
        const content = `<div class="demo-item"><b>您的姓名</b>：${res.name}</div>`
        $(".user").append(content);
    });

    $.get("http://127.0.0.1:8080/position/0", function (res) {
        const content = `<div class="demo-item"><b>您所在的位置</b>：${res.city}</div>`
        $(".position").append(content);
    });

    $.get("http://127.0.0.1:8080/position/code/0", function (res) {
        var color;
        if (res.status == 0) {
            color = `<font color="#00ff00">否</font>`
        } else if (res.status == 1) {
            color = `<font color="#FF0000">是</font>`
        }
        const content = `<div class="demo-item"><b>您是否经过热门春运地域</b>：${color} </div>`
        $(".code").append(content);
    });

    $.get("http://127.0.0.1:8080/news", function (res) {
        const fragment = document.createDocumentFragment();

        for (let i = 0; i < res.length; i++) {
            const li = document.createElement('li');
            li.innerHTML = res[i].content;
            fragment.appendChild(li);
        }

        const title = `<div class="demo-item"><b>新闻通知</b>：</div>`;

        $(".news").append(title);
        $(".news").append(fragment);
    });

    $.get("http://127.0.0.1:8080/rank", function (res) {
        //创建table表格  
        const table = document.createElement("table");
        table.style.border = '1px solid gray';

        const thead = document.createElement('thead');
        table.appendChild(thead);

        const tr = document.createElement('tr');
        thead.appendChild(tr);

        const tbody = document.createElement('tbody');
        table.appendChild(tbody);

        const headDates = ['热力值', '省份'];
        for (let i = 0; i < headDates.length; i++) {
            const td = document.createElement('td');
            td.style.textAlign = 'center';
            td.style.border = '1px solid gray';
            td.style.height = '10px';
            td.style.width = '120px';
            td.innerHTML = headDates[i];
            tr.appendChild(td);
        }

        for (let i = 0; i < res.length; i++) {
            const item = res[i];
            //创建表体数据的行与表体绑定
            const tr = document.createElement('tr');
            tbody.appendChild(tr);
            for (let key in item) {
                //创建表体行中的列
                const td = document.createElement('td');
                td.style.border = '1px solid gray';
                td.style.textAlign = 'center';
                td.innerHTML = item[key];
                tr.appendChild(td);
            }
        }

        const title = `<div class="demo-item"><b>迁入迁出排名</b>：</div>`

        $(".rank").append(title);
        $(".rank").append(table);
    });
}

function judge(a) {
    var b;
    return a < 10 ? b = '0' + a : b = '' + a;
}
function showtime() {
    var mytime = new Date();
    var str = '';
    str += mytime.getFullYear() + '-' + judge(mytime.getMonth() + 1) + '-' + judge(mytime.getDate()) + ' ' + judge(mytime.getHours()) + ':' + judge(mytime.getMinutes()) + ':' + judge(mytime.getSeconds());
    return `<div class="demo-date">${str}</div>`;
}

function createBox(boxs) {
    const fragment = document.createDocumentFragment();

    for (let i = 0; i < boxs.length; i++) {
        const div = document.createElement('div');
        div.setAttribute('class', boxs[i])
        fragment.appendChild(div);
    }

    return fragment;
}
