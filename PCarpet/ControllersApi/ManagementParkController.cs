using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace PCarpet.ControllersApi
{
    public class ManagementParkController : ApiController
    {
        private StopService stopService;

        public ManagementParkController()
        {
           this.stopService = new StopService();
        }

        //non serve piu'
        [HttpGet]
        [Route("api/getAllStopInSlotsUser")]
        public List<ManagementCarPlaceDTO> ManagementParkControl()
        {
            List<ManagementCarPlaceDTO> managementCarPlaceDTO = stopService.getAllStopDTOByCurrentUser();
            return managementCarPlaceDTO;
        }
    }
}
