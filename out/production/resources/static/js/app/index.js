var main = {
    init : function () {
        var _this = this;
        var today = new Date();
        today.setHours(0,0,0,0);
        $('#btn-go-save').on('click', function () {
            if(parseFloat($("#remainDays").text())<= 0){
                alert("잔여 연차가 없습니다.");
            }else{
                location.href = "/vacations/save";
            }
        });
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $("button[name=btn-cancel]").on('click', function () {
            _this.cancel($(this));
        });
        //휴가타입선택
        $('#type').on("change",function (){
            $('#startDate,#endDate').datetimepicker('date', today);
            if(this.value=='0.5' || this.value=='0.25'){
                $("#endDateGroup").hide();
            }else{
                $("#endDateGroup").show();
            }
            $("#useDay").val(this.value);//default 값 설정
        });

        //달력 초기화
        $('#startDate, #endDate').datetimepicker({ format: 'YYYY-MM-DD',daysOfWeekDisabled: [0, 6], useStrict:true, minDate:today});
        //$('').datetimepicker({ format: 'YYYY-MM-DD', daysOfWeekDisabled: [0, 6], useStrict:true, minDate: new Date()});
        $("#startDate").on("change.datetimepicker", ({date, oldDate}) => {
            if($('#type option:selected').attr('value')=='1' && $("#endDate").is(":visible")){
                 var sd = new Date(date);
                 var ed = new Date($('#endDate').datetimepicker('viewDate'));
                 getBusinessDay(sd,ed);
             }
        });
        $("#endDate").on("change.datetimepicker", ({date, oldDate}) => {
            if($('#type option:selected').attr('value')=='1' && $("#endDate").is(":visible")){
                 var sd = new Date($('#startDate').datetimepicker('viewDate'));
                 var ed = new Date(date);
                 getBusinessDay(sd,ed);
             }
        });

    },
    save : function () {
        if (parseFloat($("#remainDays").text())-$('#useDay').val()<0){
            alert("사용가능한 연차 개수를 초과하였습니다.");
            return false;
        }
        var type= $("#type option:selected").val();
        var startDate = $('#startDate').datetimepicker('viewDate').format('YYYY-MM-DD');
        var endDate = type!='1'?startDate:$('#endDate').datetimepicker('viewDate').format('YYYY-MM-DD');
        var data = {
            uid: $('#uid').val(),
            type: $("#type option:selected").text(),
            startDate: startDate,
            endDate: endDate,
            useDay: $('#useDay').val(),
            comment: $('#comment').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/vacations',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('휴가 신청되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    cancel : function (obj) {
        var tr = obj.parent().parent();
        var td = tr.children();
        var startDate = td.eq(2).text();
        if(new Date(startDate).setHours(0,0,0,0) <= new Date().setHours(0,0,0,0)){
            alert('이미 시작한 휴가는 취소할 수 없습니다.');
            return false;
        }
        var data = {
            uid: $('#uid').val(),
            useDay: td.eq(4).text(),
            cancelYN: 'Y'
        };

        $.ajax({
            type: 'PUT',
            url: '/api/v1/vacations/'+td.eq(0).text(),
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('휴가 취소되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();

function getBusinessDay(sd,ed){
    var count = 0;
    while(true) {
        var temp_date = sd;
        if(sd.getTime() > ed.getTime()) {
            break;
        } else {
            var tmp = temp_date.getDay();
            if(tmp == 0 || tmp == 6) {
            } else {
                count++;
            }
            temp_date.setDate(sd.getDate() + 1);
        }
    }
    if ( sd!=ed & count == 0){
        alert("종료일이 시작일보다 이전일수 없습니다");
        var today = new Date();
        today.setHours(0,0,0,0);
        $('#startDate,#endDate').datetimepicker('date', today);
        $("#useDay").val('1');
        return false;
    }
    $("#useDay").val(count);
    return true;
}

