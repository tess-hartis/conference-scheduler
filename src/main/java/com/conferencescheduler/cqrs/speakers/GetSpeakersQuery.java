package com.conferencescheduler.cqrs.speakers;

import an.awesome.pipelinr.Command;
import com.conferencescheduler.domain.Speaker;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class GetSpeakersQuery implements Command<List<Speaker>> { }
