using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")] 
    [RoutePrefix("api")]
    public class FindCarPlaceController : ApiController
    {
        private SlotService slotService;
        private StopService stopService;

        public FindCarPlaceController()
        {
            this.slotService = new SlotService();
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("getCarWithoutStop")]
        public List<CarDTO> getCarWithoutStopOfUser(string username)
        {
            List<CarDTO> cars = stopService.getCarWithoutStop(username);
            return cars;

        }

        [HttpGet]
        [Route("updateParkings")]
        public List<SlotDTO> updateParkings(string lat, string lng)
        {
            double latitude = double.Parse(lat, CultureInfo.InvariantCulture);
            double longitude = double.Parse(lng, CultureInfo.InvariantCulture);
            // calcolo slot vicini e li aggiungo a Map chiamata al service
            List<SlotDTO> slots = slotService.getNearSlot(latitude, longitude);
            return slots;
        }
    }
}
