using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using Newtonsoft.Json;

using Xamarin.Forms;
using Xamarin.Forms.Xaml;

namespace BlogPeliculasMobile
{
    [XamlCompilation(XamlCompilationOptions.Compile)]
    public partial class ManageMovies : ContentPage
    {
        string url = "https://sqliteazurepelicula.azurewebsites.net";
        public ManageMovies()
        {
            InitializeComponent();
        }

        private async void handleAddMovie(object sender, EventArgs e)
        {
            var webClient = new HttpClient();


            webClient.BaseAddress = new Uri(url);
            webClient
                .DefaultRequestHeaders
                .Accept
                .Add(System.Net.Http.Headers.MediaTypeWithQualityHeaderValue.Parse("application/json"));

            var json = JsonConvert.SerializeObject(new Pelicula
            {
                Titulo = txtTitulo.Text,
                EnCines = checkBox.IsChecked,
                FechaEstreno = txtDate.Date
            });


            var request = new HttpRequestMessage(HttpMethod.Post, url + "/api/Peliculas");
            request.Content = new StringContent(json, Encoding.UTF8, "application/json");

            var resp = webClient.SendAsync(request);
            resp.Wait();

            json = resp.Result.Content.ReadAsStringAsync().Result;
            var prod = JsonConvert.DeserializeObject<Pelicula>(json);

            txtId.Text = prod.Id.ToString();

        }

        private async void handleUpdateMovie(object sender, EventArgs e)
        {
            var client = new HttpClient();

            client.BaseAddress = new Uri($"{url}/api/Peliculas/{txtId.Text}");
            client
                .DefaultRequestHeaders
                .Accept
                .Add(System.Net.Http.Headers.MediaTypeWithQualityHeaderValue.Parse("application/json"));

            var json = JsonConvert.SerializeObject(new Pelicula
            {
                Id = int.Parse(txtId.Text),
                Titulo = txtTitulo.Text,
                EnCines = checkBox.IsChecked,
                FechaEstreno = txtDate.Date
            });

            var rqst = new HttpRequestMessage(HttpMethod.Put, url);
            rqst.Content = new StringContent(json, Encoding.UTF8, "application/json");

            var resp = client.SendAsync(rqst);
            resp.Wait();



        }

        private async void handleReadMovie(object sender, EventArgs e)
        {
            var webClient = new HttpClient();
            var resp = webClient.GetStringAsync(url + "/api/Peliculas/" + txtId.Text);
            resp.Wait();

            var json = resp.Result;
            var prod = JsonConvert.DeserializeObject<Pelicula>(json);

            txtId.Text = prod.Id.ToString();
            txtTitulo.Text = prod.Titulo;
            checkBox.IsChecked = prod.EnCines;
            txtDate.Date = prod.FechaEstreno;


        }

        private async void handleDeleteMovie(object sender, EventArgs e)
        {
            var client = new HttpClient();
            client.BaseAddress = new Uri($"{this.url}/api/Peliculas/{txtId.Text}");
            client
                .DefaultRequestHeaders
                .Accept
                .Add(System.Net.Http.Headers.MediaTypeWithQualityHeaderValue.Parse("application/json"));

            var resp = client.DeleteAsync(url);
            resp.Wait();

            txtId.Text = "";
            txtTitulo.Text = "";
            checkBox.IsChecked = false;
            txtDate.Date = new DateTime();


        }


        private async void handleReturn(object sender, EventArgs e)
        {
            await Navigation.PopAsync();
        }

    }
}