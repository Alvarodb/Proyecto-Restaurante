/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(function () {
    var includes = $('[data-include]');
    jQuery.each(includes, function () {
        var file = $(this).data('include') + '.html';
        $(this).load(file);
    });
});

