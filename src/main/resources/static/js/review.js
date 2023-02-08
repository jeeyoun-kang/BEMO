var input =document.querySelector('input[name=hashtag]');
var tagify = new Tagify(input, {
    whitelist:  ["반전", "감동", "액션", "역사"],
    maxTags: 2,
    userInput: false,

    dropdown: {
        position: "input",
        classname: "tagsLook",
        enabled: 0,
        closeOnSelect: false
    }
})
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-comment').on('click', function () {
            _this.comment();
        });
    },
    comment : function (){

        var id = $('#id').text();
        console.log(id);
        $.ajax({
            type:'GET',
            url:'/users/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert("성공");
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    save : function () {
        const form = $('#excelForm')[0];
        const formData = new FormData(form);
        const hashtagdata = $('input[name=hashtag]').val();
        console.log(hashtagdata);
        let hashtagData = "empty";
        if(hashtagdata != ""){
            const test = JSON.parse(hashtagdata);
            hashtagData = "";
            for (const i in test) {
                hashtagData += test[i].value + ",";
            }
            hashtagData = hashtagData.substring(0, hashtagData.length - 1);
        }
        const data = {
            title: $('#title').val(),
            author: $('#author').val(),
            authorname: $('#authorname').val(),
            content: $('#content').val(),
            mvtitle: $('#mvtitle').text(),
            hashtag: hashtagData,
        };
        console.log("해시태그="+data.hashtag);
        formData.append('file', $('#file'));
        var filename=formData.get('file').name;
        formData.append('key', new Blob([ JSON.stringify(data) ], {type : "application/json"}));
        console.log(formData);

        $.ajax({
            type: 'POST',
            url: '/users/',
            data: formData,
            contentType: false,               // * 중요 *
            processData: false,               // * 중요 *
            enctype : 'multipart/form-data',  // * 중요 *
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function (){
        const form = $('#excelForm')[0];
        const formData = new FormData(form);
        const hashtagdata = $('input[name=hashtag]').val();
        console.log(hashtagdata);
        let hashtagData = "empty";
        if(hashtagdata != ""){
            const test = JSON.parse(hashtagdata);
            hashtagData = "";
            for (const i in test) {
                hashtagData += test[i].value + ",";
            }
            hashtagData = hashtagData.substring(0, hashtagData.length - 1);
        }
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            mvtitle: $('#mvtitle').text(),
            hashtag: hashtagData,
        };
        var id = $('#update_id').text();
        console.log(id);
        //var update_id=$('#update_id').text()
        console.log("해시태그="+data.hashtag);
        formData.append('file', $('#file'));
        formData.append('key', new Blob([ JSON.stringify(data) ], {type : "application/json"}));

        $.ajax({
            type: 'PUT',
            url: '/users/'+id,
            data: formData,
            contentType: false,               // * 중요 *
            processData: false,               // * 중요 *
            enctype : 'multipart/form-data',  // * 중요 *
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    delete : function (){


        var id = $('#update_id').text();

        $.ajax({
            type:'DELETE',
            url:'/users/'+id,
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();