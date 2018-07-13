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
    [EnableCors(origins: "*", headers: "*", methods: "*")] // tune to your needs
    [RoutePrefix("api")]

    public class ExtensionstopsController : ApiController
    {
        private UserService userService;
        private StopService stopService;

        public ExtensionstopsController()
        {
            this.userService = new UserService();
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("myStopsList")]
        public List<ManagementExtensionStopDTO> extensionStops()
        {
            user user = userService.getLoggedUser();
            List<ManagementExtensionStopDTO> managementExtensionStopDTO;
            managementExtensionStopDTO = stopService.getAllExtensionStop(user);
            //ViewBag.stops = managementExtensionStopDTO;
            return managementExtensionStopDTO;
        }

        [HttpPut]
        //  [Route("extendStop")]
        public string Put(int id, [FromBody] int minute)
        {
            
            System.Diagnostics.Debug.WriteLine(id.ToString());
            System.Diagnostics.Debug.WriteLine(minute.ToString());
            return stopService.extensionStop(id,minute);
        }
    }
    }

