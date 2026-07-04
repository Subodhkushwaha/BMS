package com.cfs.BMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardResponse {

    private long totalMovies;

    private long totalTheaters;

    private long totalShows;

    private long totalBookings;

    private long totalUsers;

    private double totalRevenue;
}