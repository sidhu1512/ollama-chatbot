$(document).ready(function () {
  $('#sendBtn').click(sendMessage);
  $('#userInput').keypress(function (e) {
    if (e.which === 13) sendMessage();
  });

  function sendMessage() {
    const message = $('#userInput').val().trim();
    if (!message) return;

    $('#chatBox').append(`<div class="message user">${message}</div>`);
    $('#userInput').val('');
    scrollToBottom();

    $.ajax({
      url: 'http://localhost:8080/api/chat',
      method: 'POST',
      contentType: 'application/json',
      data: JSON.stringify({ message: message }),
      success: function (response) {
        $('#chatBox').append(`<div class="message bot">${response}</div>`);
        scrollToBottom();
      },
      error: function () {
        $('#chatBox').append(`<div class="message bot">⚠️ Error from server</div>`);
        scrollToBottom();
      }
    });
  }

  function scrollToBottom() {
    $('#chatBox').scrollTop($('#chatBox')[0].scrollHeight);
  }
});
