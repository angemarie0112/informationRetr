const baseUrl = "http://localhost:8083";
const movie_poster_baseURl = "https://image.tmdb.org/t/p/w500";
let currentPage = 1;
let totalPages = 1;
let totalItems = 1;

function isJsonString(str) {
    try {
        JSON.parse(str);
    } catch (e) {
        return false;
    }
    return true;
}

function updateGenre(genres, new_genre) {
    let newGenres = genres;
    if(!new_genre.length) return newGenres;

    // check for the current Fvalue
    new_genre.forEach(tmp_gen => {
        const check = newGenres.filter(val => val.id == tmp_gen.id);
        if(!check.length) {
            // new value not found, add the new value to the main list
            newGenres.push(tmp_gen);
        };
    });

    // return the updated new genre
    return newGenres
}

function updatelanguages(languages, new_language) {
    let newLanguages = languages;
    if(!new_language || !new_language.length) return newLanguages;

    // check for the current value
    new_language.forEach(tmp => {
        const check = newLanguages.filter(val => val.iso_639_1 == tmp.iso_639_1);
        if(!check.length) {
            // new value not found, add the new value to the main list
            newLanguages.push(tmp);
        };
    });

    // return the updated new values
    return newLanguages
}

function updateProdCompanies(companies, new_companies) {
    let newData = companies;
    if(!new_companies || !new_companies.length) return newData;

    // check for the current value
    new_companies.forEach(tmp => {
        const check = newData.filter(val => val.id == tmp.id);
        if(!check.length) {
            // new value not found, add the new value to the main list
            newData.push(tmp);
        };
    });

    // return the updated new values
    return newData;
}

function updateProductionCountries(countries, new_countries) {
    let newData = countries;
    if(!new_countries || !new_countries.length) return newData;

    // check for the current value
    new_countries.forEach(tmp => {
        const check = newData.filter(val => val.iso_3166_1 == tmp.iso_3166_1);
        if(!check.length) {
            // new value not found, add the new value to the main list
            newData.push(tmp);
        };
    });

    // return the updated new values
    return newData;
}

function updateCollections(collections, new_collection) {
    let newData = collections;
    if(!new_collection) return newData;

    // check for the current value
    const check = newData.filter(val => val.id == new_collection.id);
    if(!check.length) {
        // new value not found, add the new value to the main list
        newData.push(new_collection);
    };

    // return the updated new values
    return newData;
}

function cleanData(movieData)
{
    // initialize the return data
    let movie_data = [];
    let collections = [];
    let companies_prod = [];
    let countries_prod = [];
    let spoken_languages = [];
    let genres = [];
    
    try {
        const cleanData = movieData.map(val => {
            // initialize the data objects
            let tmp_genres = [];
            let collection = val.belongs_to_collection;
            let main_genres = val.genres;
            let languages = val.spoken_languages;
            let prod_companies = val.production_companies;
            let prod_countries = val.production_countries;

            // replace the single quotes with double quotes
            main_genres = main_genres.replace(/'/g, '"');
            languages = languages && typeof languages == "string" ? languages.replace(/'/g, '"') : null;
            prod_companies = prod_companies && typeof prod_companies == "string" ? prod_companies.replace(/'/g, '"') : null;
            prod_countries = prod_countries && typeof prod_countries == "string" ? prod_countries.replace(/'/g, '"') : null;
            collection = collection && typeof collection == "string" ? collection.replace(/'/g, '"') : null;

            // convert the extracted objects to json
            tmp_genres = isJsonString(main_genres) ? JSON.parse(main_genres) : [];
            languages = isJsonString(languages) ? JSON.parse(languages) : [];
            prod_companies = isJsonString(prod_companies) ? JSON.parse(prod_companies) : [];
            prod_countries = isJsonString(prod_countries) ? JSON.parse(prod_countries) : [];
            collection = isJsonString(collection) ? JSON.parse(collection) : "";

            // update the corresponding objects
            // genre
            collections = this.updateCollections(collections, collection);
            genres = this.updateGenre(genres, tmp_genres);
            spoken_languages = this.updatelanguages(spoken_languages, languages);
            companies_prod = this.updateProdCompanies(companies_prod, prod_companies);
            countries_prod = this.updateProductionCountries(countries_prod, prod_countries);

            // update the returned data
            return {
                id: val.id,
                imdb_id: val.imdb_id,
                isAdult: val.adult,
                title: val.original_title,
                belongsTo: collection,
                language: val.original_language,
                overview: val.overview,
                budget: val.budget,
                genres: tmp_genres,
                homepage: val.homepage,
                popularity: val.popularity,
                releaseDate: val.release_date,
                duration: val.runtime,
                spoken_languages: languages,
                status: val.status,
                average_votes: val.vote_average,
                total_votes: val.vote_count,
                companies_prod: prod_companies,
                countries_prod: prod_countries,
                poster_url: val.poster_path
            }
        });

        movie_data = cleanData;

        // update the final response
        return {
            movieData: movie_data,
            genres: genres,
            languages: spoken_languages,
            prod_companies: companies_prod,
            prod_countries: countries_prod,
            collection: collections
        }

    } catch (error) {
        console.log("An error occured :: ", error);
        return [];
    }
}

async function submitData(url, postData)
{
    return $.ajax({
        type: "POST",
        url: url,
        contentType:"application/json",
        data: postData,
        complete: function(data){
            return data;
        },
        dataType: "json"
    });
}

async function getEntityData(dataUrl){
    return $.get(dataUrl, function(responseData){
        return responseData;
    }, 'json');
}

/** storing data */
async function storeLanguages(langData = [])
{
    const lang_url = `${baseUrl}/movieCompCtrl/v1/addLanguage`;
    console.log("the language url is :: ", lang_url);

    for(let i=0; i<langData.length; i++){
        let tmp = langData[i];

        // prepare the data
        let tmpData = {
            iso_code: tmp.iso_639_1,
            languageName: tmp.name
        }

        // send the data
        await this.submitData(lang_url, JSON.stringify(tmpData));
    }
    console.log("finished spoken lnguages storage");
}

async function storeGenres(genreData = [])
{
    const service_url = `${baseUrl}/movieCompCtrl/v1/addGenre`;
    console.log("the service url is :: ", service_url);

    for(let i=0; i<genreData.length; i++){
        let tmp = genreData[i];

        // prepare the data
        let tmpData = {
            name: tmp.name
        }

        // send the data
        await this.submitData(service_url, JSON.stringify(tmpData));
    }
    console.log("finished genres storage")
}

async function storeProductionCompanies(companyData = [])
{
    const service_url = `${baseUrl}/movieCompCtrl/v1/addProdCompany`;
    console.log("the service url is :: ", service_url);

    for(let i=0; i<companyData.length; i++){
        let tmp = companyData[i];

        // prepare the data
        let tmpData = {
            name: tmp.name
        }

        // send the data
        await this.submitData(service_url, JSON.stringify(tmpData));
    }
    console.log("finished production companies storage");
}

async function storeProductionCountries(countriesData = [])
{
    const service_url = `${baseUrl}/movieCompCtrl/v1/addProdCountry`;
    console.log("the service url is :: ", service_url);

    for(let i=0; i<countriesData.length; i++){
        let tmp = countriesData[i];

        // prepare the data
        let tmpData = {
            iso_ii_code: tmp.iso_3166_1,
            countryName: tmp.name
        }

        // send the data
        await this.submitData(service_url, JSON.stringify(tmpData));
    }
    console.log("finished prod countries storage");
}

async function storeColections(collectionsData = [])
{
    const service_url = `${baseUrl}/movieCompCtrl/v1/addCollection`;
    console.log("the service url is :: ", service_url);

    for(let i=0; i<collectionsData.length; i++){
        let tmp = collectionsData[i];

        // prepare the data
        let tmpData = {
            name: tmp.name,
            posterPath: tmp.poster_path,
            backdropPath: tmp.backdrop_path
        }

        // send the data
        await this.submitData(service_url, JSON.stringify(tmpData));
    }
    console.log("finished collections storage");
}

/** storing movie components */
async function storeMovieData(movieData = [])
{
    const movie_url = `${baseUrl}/movieCtrl/v1/addMovie`;

    for(let i=0; i<500; i++){
        let tmp = movieData[i];

        // prepare the data
        let tmpData = {
            imdb_id: tmp.imdb_id,
            title: tmp.title,
            status: tmp.status,
            budget: tmp.budget,
            overview: tmp.overview,
            isAdult: tmp.isAdult,
            mainLanguage: tmp.language,
            releaseDate: tmp.releaseDate,
            duration: tmp.duration,
            homepageLink: tmp.homepage,
            posterUrl: tmp.poster_url,
            popularity: tmp.popularity,
            averageVotes: tmp.average_votes,
            totalVotes: tmp.total_votes
        }

        // send the data
        this.submitData(movie_url, JSON.stringify(tmpData)).then(async (res) => {
            console.log(res)
            if(res == 1) {
                // prepare the data
                let mv_collection = [tmp.belongsTo];
                let mv_prod_comp = tmp.companies_prod;
                let mv_prod_country = tmp.countries_prod;
                let mv_genres = tmp.genres;
                let mv_languages = tmp.spoken_languages;

                // store the data
                await this.storeMovieCollection(tmp.imdb_id, mv_collection);
                await this.storeMovieGenres(tmp.imdb_id, mv_genres);
                await this.storeMovieProdCompany(tmp.imdb_id, mv_prod_comp);
                await this.storeMovieProdCountry(tmp.imdb_id, mv_prod_country);
                await this.storeMovieLanguage(tmp.imdb_id, mv_languages);
            }
        })
    }
    // end movie data storage
    console.log("Done updating movie data and associated components");
}

async function storeMovieCollection(mvid, collectionData = [])
{
    for(let i=0; i<collectionData.length; i++){
        let tmp = collectionData[i];

        // prepare the url
        if(!tmp) continue;
        let tmp_url = `${baseUrl}/movieCtrl/v1/addMovieCollection`;
        let tmpData = {
            mvImdb: mvid,
            collectionName: tmp.name
        }

        // send the data
        await this.submitData(tmp_url, JSON.stringify(tmpData));
    }
}

async function storeMovieGenres(mvid, genreData = [])
{
    for(let i=0; i<genreData.length; i++){
        let tmp = genreData[i];

        // prepare the url
        if(!tmp) continue;
        let tmp_url = `${baseUrl}/movieCtrl/v1/addMovieGenre`;
        let tmpData = {
            mvImdb: mvid,
            genreName: tmp.name
        }
        // send the data
        await this.submitData(tmp_url, JSON.stringify(tmpData));
    }
}

async function storeMovieProdCompany(mvid, companyData = [])
{
    for(let i=0; i<companyData.length; i++){
        let tmp = companyData[i];

        // prepare the url
        if(!tmp) continue;
        let tmp_url = `${baseUrl}/movieCtrl/v1/addMovieProdCompany`;
        let tmpData = {
            mvImdb: mvid,
            companyName: tmp.name
        }
        // send the data
        await this.submitData(tmp_url, JSON.stringify(tmpData));
    }
}

async function storeMovieProdCountry(mvid, countryData = [])
{
    for(let i=0; i<countryData.length; i++){
        let tmp = countryData[i];

        // prepare the url
        if(!tmp) continue;
        let tmp_url = `${baseUrl}/movieCtrl/v1/addMovieProdCountry`;
        let tmpData = {
            mvImdb: mvid,
            countryIso: tmp.iso_3166_1
        }
        // send the data
        await this.submitData(tmp_url, JSON.stringify(tmpData));
    }
}

async function storeMovieLanguage(mvid, languageData = [])
{
    for(let i=0; i<languageData.length; i++){
        let tmp = languageData[i];

        // prepare the url
        if(!tmp) continue;
        let tmp_url = `${baseUrl}/movieCtrl/v1/addMovieLanguage`;
        let tmpData = {
            mvImdb: mvid,
            langCode: tmp.iso_639_1
        }
        // send the data
       await this.submitData(tmp_url, JSON.stringify(tmpData));
       
    }
}

function fetchMovieImages()
{
    return Math.floor(Math.random() * 61);
}

async function displayMovies(moverRes = {})
{
    try {
        // start the spinner
        const spinner = `<div class="text-center">
                            <span class="spinner-border text-primary"></span>
                        </div>`;

        $(".movie_listing").html(spinner);

        const movieData = moverRes.movies;
        totalItems = moverRes.totalItems ? moverRes.totalItems : 1;
        totalPages = moverRes.totalPages ? moverRes.totalPages : 1;
        currentPage = moverRes.currentPage ? moverRes.currentPage : 1;

        if(!movieData || !movieData.length) return 0;
        let htmlStr = ``;
        for (let i = 0; i < movieData.length; i++) {
            const movie = movieData[i];
            let collection = movie.collections ? movie.collections : null;
            let poster = ``;
            if(collection) {
                poster = movie_poster_baseURl + "/" + collection.cllection.posterPath;
            }
            let date = new Date(movie.releaseDate);

            const imgUrl = `img/${this.fetchMovieImages()}.jpeg`;

            // display the movie
            htmlStr += `<div class="movie_card">
                            <div class="movie_cardBody border rounded-2 mb-1">
                                <img class="rounded-2" src="${imgUrl}" height="100%" width="100%" alt="someimg">
                            </div>
                            <div class="movie_cardFooter text-white">
                                <!-- the movie links -->
                                <div class="movie_footer_links d-flex justify-content-between text-light">
                                    <small>${date.getFullYear()}</small>
                                    <small class="border rounded-pill px-2">MOVIE</small>
                                    <small>${movie.duration} min</small>
                                </div>
                        
                                <!-- the movie title -->
                                <div class="mb-2 movie_title">
                                    <span>${movie.title}</span>
                                </div>
                            </div>
                        </div>`

        }

        // end of the movie data update
        $(".movie_listing").html(htmlStr);
        setPagination();

    } catch (error) {
        console.log(error);
        const emptyData = `<div class="text-center text-bold">
                            <span class="text-light">No Movie Found</span>
                        </div>`;

        $(".movie_listing").html(emptyData);
        return 0;
    }
}

async function fetchMovieData(filters){
    // let queryParams = `page=${filters.page}`;
    // for(const key in filters){
    //     if(filters.hasOwnProperty(key)){
    //         if(!filters[key]) continue;
    //         if(key != "page") queryParams += `&${key}=${filters[key]}`;
    //     }
    // }
    let url = `${baseUrl}/movieCtrl/v1/searchMovies`;
    console.log("The base url is :: ", url);
    return submitData(url, JSON.stringify(filters));
}

function searchMovies()
{
    // get the set size
    const size = $("#movieSize").val();
    const searchKey = $("#shFilter").val();
    const searchValue = $("#main_search").val();

    const filter = {
        page: currentPage,
        size: Number(size)
    }

    filter[searchKey] = searchValue;
    console.log(filter)

    fetchMovieData(filter).then(res => {
        console.log(res);
        displayMovies(res);
    })
}

function nextPage()
{
    currentPage = currentPage < totalPages ? currentPage + 1 : totalPages;
    searchMovies()
}

function prevPage()
{
    currentPage = currentPage > 1 ? currentPage - 1 : currentPage;
    searchMovies()
}

function setPage(newPageNumber)
{
    currentPage = newPageNumber;
    searchMovies();
}

function setPagination()
{
    
    let prev = `<li class="page-item" onclick="prevPage()">
                    <span class="page-link">Previous</span>
                </li>`;
    let pageStr = prev;
    let next = `<li class="page-item" onclick="nextPage()">
                    <span class="page-link">Next</span>
                </li>`;

    let pageStart = currentPage;
    let pagesShown = currentPage + 5;
    pagesShown = pagesShown > totalPages ? totalPages : pagesShown;
    for (let i = pageStart; i < pagesShown; i++) {
        pageStr += `<li class="page-item" onclick="setPage(${i})">
                        <span class="page-link">${i}</span>
                    </li>`
    }
    pageStr += next;

    // update the pagination list
    $(".pagination").html(pageStr)

}



$(document).ready(() => {

    searchMovies();
    // get the movies data
    // let size = $("#movieSize").val();
    // let page = 1;
    // let filters = {
    //     page: page,
    //     size: size
    // }
    // console.log(filters)

    // fetchMovieData(filters).then(res => {
    //     console.log(res);
    //     displayMovies(res);
    // })

    // $.get("movie.json", async (movieData) => {
    //     // console.log(movieData);
    //     const cleanData = this.cleanData(movieData);

    //     console.log(cleanData)

    //     displayMovies(cleanData.movieData).then(res => {
    //         if(!res) {
    //             console.log(res)
    //         }
    //     });

    //     // store the languages
    //     // this.storeLanguages(cleanData.languages);

    //     // store the genres
    //     // this.storeGenres(cleanData.genres);

    //     // store collections
    //     // this.storeColections(cleanData.collection);

    //     // store production companies
    //     // this.storeProductionCompanies(cleanData.prod_companies);

    //     // store production countries
    //     // this.storeProductionCountries(cleanData.prod_countries);

    //     // store the movie data
    //     // storeMovieData(cleanData.movieData);
    // })
})





