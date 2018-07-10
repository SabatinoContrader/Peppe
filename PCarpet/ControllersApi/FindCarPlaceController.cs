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
    public class FindCarPlaceController : ApiController
    {
        private StopService stopService;

        public FindCarPlaceController()
        {
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("api/getCarWithoutStopOfUser")]
        public List<CarDTO> getCarWithoutStopOfUser()
        {
            List<CarDTO> cars = stopService.getCarWithoutStopOfUser();
            return cars;

        }
    }
}
