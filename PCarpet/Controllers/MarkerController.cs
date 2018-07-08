using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
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

        //public MarkerController(SlotService slotService, StopService stopService)
        //{
        //    this.slotService = slotService;
        //    this.stopService = stopService;
        //}

        public MarkerController()
        {
            this.slotService = new SlotService(); 
            this.stopService = new StopService();
        }

        [HttpPost]
        [Route("api/updateParkings")]
        public List<SlotDTO> updateParkings(String latitude, String longitude)
        {

            System.Diagnostics.Debug.WriteLine("wewewewewe");
            Double lat = Double.Parse(latitude);
            Double lng = Double.Parse(longitude);

            // calcolo slot vicini e li aggiungo a Map chiamata al service
            List<SlotDTO> slots = slotService.getNearSlot(lat, lng);

            return slots;

            // trattandosi di una chiamata AJAX metto i valori di ritorno dentro response e
            // NON richiamo la view
        }

        [HttpGet]
        [Route("api/updateSlots")]
        public IEnumerable<ManagementCarPlaceDTO> updateSlots()
        {
            //List<ManagementCarPlaceDTO>
            System.Diagnostics.Debug.WriteLine("wewewewewe2");
            List<ManagementCarPlaceDTO> managementCarPlaceDTOs = new List<ManagementCarPlaceDTO>();
            managementCarPlaceDTOs = stopService.getAllStopDTOByCurrentUser();

            //foreach (ManagementCarPlaceDTO dto in managementCarPlaceDTOs)
            //{
            //    System.Diagnostics.Debug.WriteLine(dto.slot.id);
            //    System.Diagnostics.Debug.WriteLine(dto.slot.number_carplace);
            //    System.Diagnostics.Debug.WriteLine(dto.slot.price);

            //    foreach (StopDTO stop in dto.stop_list)
            //    {
            //        System.Diagnostics.Debug.WriteLine("");
            //        System.Diagnostics.Debug.WriteLine(stop.id);
            //        System.Diagnostics.Debug.WriteLine(stop.start);
            //        System.Diagnostics.Debug.WriteLine(stop.finish);
            //    }

            //    System.Diagnostics.Debug.WriteLine(""); System.Diagnostics.Debug.WriteLine(""); System.Diagnostics.Debug.WriteLine(""); System.Diagnostics.Debug.WriteLine("");
            //}

            return managementCarPlaceDTOs;

            //return Request.CreateResponse(HttpStatusCode.Created, managementCarPlaceDTOs);
            //return "CIAOOOOO";

        }
    }

}