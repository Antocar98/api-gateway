package com.xantrix.webapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.xantrix.webapp.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig 
{
	@Autowired
	private JwtAuthenticationFilter jwtAuthFilter;

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) 
	{
		return builder
				.routes()
					.route("articoliModule", r -> r
							.path("/api/articoli/**")
							.filters(f -> f.filter(jwtAuthFilter))
							.uri("lb://ArticoliWebService"))
					.route("prezziModule", r -> r
							.path("/api/prezzi/**")
								//.and().method("GET,POST,DELETE")
							.filters(f -> f.filter(jwtAuthFilter))
							.uri("lb://PriceArtService"))
					.route("listinoModule", r -> r
							.path("/api/listino/**")
								//.and().method("GET")
							.filters(f -> f.filter(jwtAuthFilter))
							.uri("lb://PriceArtService"))
				.build();
								
	}
}
