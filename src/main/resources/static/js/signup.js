var main = {
    init : function () {
        var _this = this;

        $('#btn-lg').on('click', function () {
            _this.joinCheck();
        });
    },

    joinCheck: function () {
        const id = document.getElementById("username");
        const pwd = document.getElementById("password");
        const repwd = document.getElementById("passwordCheck");
        const nickname = document.getElementById("nickname");
        const cellphone = document.getElementById("cellphone");
        const birthday = document.getElementById("birthday");

        if (id.value == "") {
            alert("아이디를 입력하세요.")
            id.focus();
            return false;
        }

        if (nickname.value == "") {
            alert("닉네임을 입력하세요.")
            nickname.focus();
            return false;
        }

        if (pwd.value == "") {
            alert("비밀번호를 입력하세요.")
            pwd.focus();
            return false;
        }
        const pwdCheck = /^(?=.*[a-zA-z])(?=.*[!@#$%^&*])(?=.*[0-9]).{8,25}$/;

        if (!pwdCheck.test(pwd.value)) {
            alert("비밀번호는 영문자+숫자+특수문자(!@#$%^&*) 조합으로 8~25자리 사용해야합니다.")
            pwd.focus();
            return false;
        }

        if (pwd.value != repwd.value) {
            alert("비밀번호가 일치하지 않습니다.")
            repwd.focus();
            return false;
        }

        const cellphoneCheck = /\d{3}-\d{4}-\d{4}/;
        if (cellphone.value == "" || !cellphoneCheck.test(cellphone.value)) {
            alert("전화번호가 올바르지 않습니다.")
            cellphone.focus();
            return false;
        }

        const data = {
            username: $('#username').val(),
            password: $('#password').val(),
            cellphone: $('#cellphone').val(),
            birthday: $('#birthday').val(),
            nickname: $('#nickname').val(),
        };
        console.log(JSON.stringify(data));

        $.ajax({
            type: 'POST',
            url: '/join',
            data: JSON.stringify(data),
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function () {
            alert('가입이 완료되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();