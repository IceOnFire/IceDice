$(function() {
	$('#grid').on('click', 'button', function(e) {
		var sum = 0;

		e.preventDefault();

		$(this).text(Math.floor(Math.random() * 10) + 1);

		$('#grid button').each(function() {
			var value = parseInt($(this).text());
			sum += isNaN(value) ? 0 : value;
		});
		$('#result').val(sum);
	});

	$('#actions').on('click', 'button', function(e) {
		e.preventDefault();
		$('#grid button').click();
	});
});
