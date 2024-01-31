using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace BlogPeliculasMobile
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class MainPage : ContentPage
    {
        public MainPage()
        {
            InitializeComponent();
        }

        private async void handleRedirectManageMovies(object sender, EventArgs e)
        {
            var manageMoviesPage = new ManageMovies();
            await Navigation.PushAsync(manageMoviesPage);
        }
    }
}