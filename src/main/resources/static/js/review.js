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
    },
    save : function (name) {
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
            url: '/{movie_title}/review/posts',
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
        var form = $('#excelForm')[0];
        var formData = new FormData(form);
        var data = {
            title: $('#title').val(),
            content: $('#content').val(),
            id: $('#update_id').text()
        };
        //var update_id=$('#update_id').text()
        console.log(data.id);
        formData.append('file', $('#file'));
        formData.append('key', new Blob([ JSON.stringify(data) ], {type : "application/json"}));

        $.ajax({
            type: 'POST',
            url: '/{title}/update/posts',
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


        var id = $('#id').val();

        $.ajax({
            type:'DELETE',
            url:'/posts/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다.');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();