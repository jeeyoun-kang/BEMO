
var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function ( ) {
        const data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val(),
            mvtitle: $('#mvtitle').text()

        };
        console.log("작성자="+data.author);
        $.ajax({
            type: 'POST',
            url: '/{movie_title}/review/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    // update : function (){
    //     var data = {
    //         title: $('#title').val(),
    //         content: $('#content').val()
    //     };
    //
    //     var id = $('#id').val();
    //
    //     $.ajax({
    //         type:'PUT',
    //         url:'/api/v1/posts/'+id,
    //         dataType:'json',
    //         contentType:'application/json; charset=utf-8',
    //         data:JSON.stringify(data)
    //     }).done(function(){
    //         alert('글이 수정되었습니다.');
    //         window.location.href='/';
    //     }).fail(function(error){
    //         alert(JSON.stringify(error));
    //     });
    // },
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