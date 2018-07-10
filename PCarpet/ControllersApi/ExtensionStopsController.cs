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
    public class ExtensionStopsController : ApiController
    {
        private UserService userService;
        private StopService stopService;

        public ExtensionStopsController()
        {
            this.userService = new UserService();
            this.stopService = new StopService();
        }

        [HttpGet]
        [Route("api/myStopsList")]
        public List<ManagementExtensionStopDTO> extensionStops()
        {
            user user = userService.getLoggedUser();
            List<ManagementExtensionStopDTO> managementExtensionStopDTO;
            managementExtensionStopDTO = stopService.getAllExtensionStop(user);
            //ViewBag.stops = managementExtensionStopDTO;
            return managementExtensionStopDTO;
        }

        [HttpPut]
        [Route("api/extendStop")]
        public void extendStop(String minute, String id, String address, String name, DateTime start, DateTime finish, String price)
        {

            user user = userService.getLoggedUser();

            int minutes = Int32.Parse(minute);
            int id_stop = Int32.Parse(id);
            float _price = float.Parse(price);
            DateTime updateddate = finish.AddMinutes(minutes);

            ManagementExtensionStopDTO item = new ManagementExtensionStopDTO(id_stop, address, start, updateddate, name, _price);
            stopService.extensionStop(item);
            //serviva per aggiornare la lista dopo la extend
            //List<ManagementExtensionStopDTO> managementExtensionStopDTO = stopService.getAllExtensionStop(user);
            //ViewBag.stops = managementExtensionStopDTO;
        }
    }
    }

