using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PCarpet.Controllers
{
    public class MarkerController : ApiController
    {
        SlotService slotService;
        StopService stopService;

        public MarkerController()
        {
            this.slotService = new SlotService(); 
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("api/updateParkings")]
        public List<SlotDTO> updateParkings(string lat, string lng)
        {
            double latitude = double.Parse(lat, CultureInfo.InvariantCulture);
            double longitude = double.Parse(lng, CultureInfo.InvariantCulture);
            // calcolo slot vicini e li aggiungo a Map chiamata al service
            List<SlotDTO> slots = slotService.getNearSlot(latitude, longitude);
            return slots;

            // trattandosi di una chiamata AJAX metto i valori di ritorno dentro response e
            // NON richiamo la view
        }

        [HttpGet]
        [Route("api/updateSlots")]
        public IEnumerable<ManagementCarPlaceDTO> updateSlots()
        {
            List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new List<ManagementCarPlaceDTO>();
            managementCarPlaceDTOs = stopService.getAllStopDTOByCurrentUser();
            return managementCarPlaceDTOs;
        }

       
    }

}