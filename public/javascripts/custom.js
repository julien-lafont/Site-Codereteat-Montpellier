$(document).ready(function() {
	$().UItoTop({ easingType: 'easeOutQuart' });
});
		
		

	$(function() {
	
		function filterPath(string) {
			return string
			.replace(/^\//,'')
			.replace(/(index|default).[a-zA-Z]{3,4}$/,'')
			.replace(/\/$/,'');
		}
	
		var locationPath = filterPath(location.pathname);
		var scrollElem = scrollableElement('html', 'body');
	
		// Any links with hash tags in them (can't do ^= because of fully qualified URL potential)
		$('a[href*=#]').each(function() {
	
			// Ensure it's a same-page link
			var thisPath = filterPath(this.pathname) || locationPath;
			if (  locationPath == thisPath
				&& (location.hostname == this.hostname || !this.hostname)
				&& this.hash.replace(/#/,'') ) {
	
					// Ensure target exists
					var $target = $(this.hash), target = this.hash;
					if (target) {
	
						// Find location of target
						var targetOffset = $target.offset().top;
						$(this).click(function(event) {
	
							// Prevent jump-down
							event.preventDefault();
	
							// Animate to target
							$(scrollElem).animate({scrollTop: targetOffset}, 400, function() {
	
								// Set hash in URL after animation successful
								location.hash = target;
	
							});
						});
					}
			}
	
		});
	
		// Use the first element that is "scrollable"  (cross-browser fix?)
		function scrollableElement(els) {
			for (var i = 0, argLength = arguments.length; i <argLength; i++) {
				var el = arguments[i],
				$scrollElement = $(el);
				if ($scrollElement.scrollTop()> 0) {
					return el;
				} else {
					$scrollElement.scrollTop(1);
					var isScrollable = $scrollElement.scrollTop()> 0;
					$scrollElement.scrollTop(0);
					if (isScrollable) {
						return el;
					}
				}
			}
			return [];
		}
	
	});
		
	// Cufon text replacememnt, add more elements to replace text with cufon generated text.
	Cufon.replace('h1');

			Cufon.replace('h2', {
				textShadow: '#333 1px 1px 0px'
			});

			Cufon.replace('h3');

			Cufon.replace('h4');		
			
			Cufon.replace('.call');
			
			Cufon.replace('.calltoaction', {
				textShadow: '#333 1px 1px 0px'
			});
			
			
			
function twitterCallback2(twitters) {
  var statusHTML = [];
  for (var i=0; i<1; i++){
    var username = twitters[i].user.screen_name;
    var status = twitters[i].text.replace(/((https?|s?ftp|ssh)\:\/\/[^"\s\<\>]*[^.,;'">\:\s\<\>\)\]\!])/g, function(url) {
      return '<a href="'+url+'">'+url+'</a>';
    }).replace(/\B@([_a-z0-9]+)/ig, function(reply) {
      return  reply.charAt(0)+'<a href="http://twitter.com/'+reply.substring(1)+'" target="_blank">'+reply.substring(1)+'</a>';
    }).replace(/\B(#[_a-z0-9]+)/ig, function(reply) {
      return '<a href="http://twitter.com/search/%23'+reply.substring(1)+'" target="_blank">'+reply+'</a>';
    });
    statusHTML.push('<a href="http://twitter.com/'+username+'/statuses/'+twitters[i].id_str+'" target="_blank">@CodeRetreatMtp</a> <span>'+status+'</span> ');
  }
  document.getElementById('twitter_update_list').innerHTML = statusHTML.join('');
}

function relative_time(time_value) {
  var values = time_value.split(" ");
  time_value = values[1] + " " + values[2] + ", " + values[5] + " " + values[3];
  var parsed_date = Date.parse(time_value);
  var relative_to = (arguments.length > 1) ? arguments[1] : new Date();
  var delta = parseInt((relative_to.getTime() - parsed_date) / 1000);
  delta = delta + (relative_to.getTimezoneOffset() * 60);

  if (delta < 60) {
    return 'moins d\'une minute';
  } else if(delta < 120) {
    return 'environ 1 minute';
  } else if(delta < (60*60)) {
    return (parseInt(delta / 60)).toString() + ' minutes';
  } else if(delta < (120*60)) {
    return 'environ 1 heure';
  } else if(delta < (24*60*60)) {
    return 'about ' + (parseInt(delta / 3600)).toString() + ' heures';
  } else if(delta < (48*60*60)) {
    return '1 jour';
  } else {
    return (parseInt(delta / 86400)).toString() + ' jours';
  }
}

