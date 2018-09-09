package com.balaji.jobs.health;

import com.yammer.metrics.core.HealthCheck;

public class TemplateHealthCheck extends HealthCheck {

	public TemplateHealthCheck() {
		super("template");
	}

	@Override
	protected Result check()  {
		return Result.healthy();
	}
}
