   // your page initialization code here
   // the DOM will be available here



 // Note: This example requires that you consent to location sharing when
      // prompted by your browser. If you see the error "The Geolocation service
      // failed.", it means you probably did not give permission for the browser to
      // locate you.

      let longitud = -34.397;
      let latitud = 150.644;
      let myMap;
      
      function initMap() {
        myMap = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -34.397, lng: 150.644},
          zoom: 6
        });
        var infoWindow = new google.maps.InfoWindow({map: myMap});

        // Try HTML5 geolocation.
        if (navigator.geolocation) {
          navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
              lat: position.coords.latitude,
              lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('Location found.');
            myMap.setCenter(pos);
          }, function() {
            handleLocationError(true, infoWindow, myMap.getCenter());
          });
        } else {
          // Browser doesn't support Geolocation
          handleLocationError(false, infoWindow, myMap.getCenter());
        }
      }


      function handleLocationError(browserHasGeolocation, infoWindow, pos) {
        infoWindow.setPosition(pos);
        infoWindow.setContent(browserHasGeolocation ?
                              'Error: The Geolocation service failed.' :
                              'Error: Your browser doesn\'t support geolocation.');
      }

      $("#searchLocation").click(function(event){
          event.preventDefault();
          if ($('#longitud').val() && $('#latitud').val()){
            myMap.setCenter({ lat: parseFloat($('#latitud').val()), lng: parseFloat($('#longitud').val()) });
          }
      })

      $("#agregarMarcador").click(function(event){
        event.preventDefault();
        if ($('#longitud').val() && $('#latitud').val()){
            myMap.setCenter({ lat: parseFloat($('#latitud').val()), lng: parseFloat($('#longitud').val()) });
            new google.maps.Marker({
              position: { lat: parseFloat($('#latitud').val()), lng: parseFloat($('#longitud').val()) },
              map: myMap,
              title:"Hello World!"
              });
          }
        
      })


      $("#calcularDistanciaConUnq").click(function(event){
        event.preventDefault();
        let latitudUnq = -34.706896;
        let longitudUnq = -58.277653;
        let latIngresed = parseFloat($('#latitud').val());
        let lonIngresed = parseFloat($('#longitud').val());

        let distanceInMeters = (google.maps.geometry.spherical.computeDistanceBetween(
          new google.maps.LatLng(-34.706896, -58.277653),
          new google.maps.LatLng(latIngresed, lonIngresed)) / 1000).toFixed(2)
        alert(distanceInMeters);
      })
