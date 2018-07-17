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
    public class MasterController : ApiController
    {
        private MasterService masterService;

        public MasterController()
        {
            this.masterService = new MasterService();
        }

        [HttpPost]
        [Route("updateSlave")]
        public void updateSlave(List<SlaveDTO> slaveDTOs)
        {
            this.masterService.updateSlave(slaveDTOs);
        }
    }
}
