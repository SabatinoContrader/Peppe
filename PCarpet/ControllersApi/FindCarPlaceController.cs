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
    public class FindCarPlaceController : ApiController
    {
        private StopService stopService;

        public FindCarPlaceController()
        {
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("getCarWithoutStop")]
        public List<CarDTO> getCarWithoutStopOfUser(string username)
        {
            List<CarDTO> cars = stopService.getCarWithoutStop(username);
            return cars;

        }
    }
}
