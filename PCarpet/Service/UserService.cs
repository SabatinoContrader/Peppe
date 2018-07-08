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

            using (pcarpetEntities context = new pcarpetEntities())
            {
                user user = context.user.FirstOrDefault(e => e.password.Equals(password) && e.username.Equals(username));

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

        public void destroyUser()
        {
            loggedUser = null;
        }

        public Boolean insertUser(user user)
        {
            using (pcarpetEntities context = new pcarpetEntities())
            {
                user userNew = context.user.Find(user.username);
                if (userNew != null)
                {
                    return false;
                }
                else
                {
                    context.user.Add(user);
                    context.SaveChanges();
                    return true;
                }
            }
        }

        //metodi interni

    }
}

