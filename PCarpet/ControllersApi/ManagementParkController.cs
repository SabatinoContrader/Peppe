using PCarpet.DTO;
using PCarpet.Service;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace PCarpet.ControllersApi
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    [RoutePrefix("api")]
    public class ManagementParkController : ApiController
    {
        private StopService stopService;

        public ManagementParkController()
        {
           this.stopService = new StopService();
        }

        [HttpGet]
        [Route("updateSlots")]
        public List<ManagementCarPlaceDTO> updateSlots(string username)
        {
            List<ManagementCarPlaceDTO> managementCarPlaceDTO = stopService.getAllManagementCarPlaceDTO(username);
            return managementCarPlaceDTO;
        }
    }
}
