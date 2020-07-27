package com.fengwenyi.mp3demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.stream.Location;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Event {
    Location location;
    double turnout;
}
