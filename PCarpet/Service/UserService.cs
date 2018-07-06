using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace PCarpet.Service
{

    public class UserService
    {
        private static user loggedUser;

        public Boolean login(String username, String password)
        {

            using (pcarpetEntities a = new pcarpetEntities())
            {
                user user = a.user.FirstOrDefault(e => e.password.Equals(password) && e.username.Equals(username));

                if (user == null)
                {
                    return false;
                }
                else
                {
                    loggedUser = user;
                    return true;
                }
            }
        }

        public user getLoggedUser()
        {
            return loggedUser;
        }
    }
}

