<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Info retrial</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="index.js?vrsion=0.1.6"></script>

    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <!-- personal css file -->
    <link rel="stylesheet" href="index.css?version=0.2.6">
</head>
<body>
    <!-- the main container -->
    <div class="container-fluid main-background" style="min-height: 100vh;">

        <!-- the heading part -->
        <div class="row mb-2 p-3 bg-dark text-white shadow">
            <!-- this is the company logo -->
            <div class="col-12 col-xl-3">
                <strong class="text-light">Movie Retrival</strong>
            </div>

            <!-- the search field -->
            <div class="col-12 col-xl-6">
                <div class="input-group">
                    <select name="shFilter" id="shFilter" class="input-group-text">
                        <option value="title">Title</option>
                        <option value="Status">Status</option>
                        <option value="language">language</option>
                        <option value="genre">Genre</option>
                        <option value="prodCountry">Production Country</option>
                        <option value="prodCompany">Production Company</option>
                        <option value="collectionName">Collection</option>
                    </select>
                    <input type="text" name="main_search" id="main_search" class="form-control">
                    <button type="button" onclick="searchMovies()" class="btn btn-primary input-group-text">Search</button>
                </div>
            </div>
        </div>

        <!-- the main body section -->
        <div class="row mb-2">
            <!-- the main col  -->
            <div class="col-12 col-xl-9">
                <!-- the filter section  -->
                <div class="row mb-2">
                    <div class="col-12">

                        <!-- the filer header  -->
                        <div class="mb-2 mt-5" hidden>
                            <strong class="text-white">FILTER</strong>
                        </div>

                        <!-- the actual filters  -->
                        <div class="mb-3 mt-4 d-fl" hidden>
                            
                            <!-- the main search filter  -->
                            <div class="me-1 mb-2">
                                <input type="text" name="searchFilter" id="searchFilter" class="form-control" placeholder="search...">
                            </div>

                            <div class="me-1 mb-2">
                                <select name="type" id="type" class="form-control">
                                    <option value="">some type</option>
                                </select>
                            </div>

                            <div class="me-1 mb-2">
                                <select name="genre" id="genre" class="form-control">
                                    <option value="">Some Genre</option>
                                </select>
                            </div>

                            <div class="me-1 mb-2">
                                <select name="country" id="country" class="form-control">
                                    <option value="">Some Country</option>
                                </select>
                            </div>

                            <div class="me-1 mb-2">
                                <select name="year" id="year" class="form-control">
                                    <option value="">Some year</option>
                                </select>
                            </div>

                            <div class="me-1 mb-2">
                                <select name="rating" id="rating" class="form-control">
                                    <option value="">Some rating</option>
                                </select>
                            </div>

                            <div class="me-1 mb-2">
                                <select name="quality" id="quality" class="form-control">
                                    <option value="">Some quality</option>
                                </select>
                            </div>

                            <div class="me-1 mb-2">
                                <button type="button" class="btn btn-primary">
                                    Filter
                                </button>
                            </div>

                        </div>

                        <div class="dataShown mb-2 text-white" style="width: 150px;">
                            <div class="d-flex align-items-baseline">
                                <span class="me-2">Movies:</span>
                                <select name="movieSize" id="movieSize" class="form-control" onchange="searchMovies()">
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select>
                            </div>
                        </div>

                        <!-- the movies listing  -->
                        <div class="mb-2 text-white movie_listing"></div>

                        <!-- pagination -->
                        <div class="mb-2">
                            <ul class="pagination">
                            </ul>
                        </div>

                    </div>
                </div>
            </div>

            <!-- the side column  -->
            <div class="col-12 col-xl-2">
                Lorem ipsum dolor sit amet consectetur adipisicing elit. Veritatis, accusantium.
            </div>
        </div>

    </div>
    
</body>
</html>