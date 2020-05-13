package org.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.AuthorizeSecurityGroupIngressRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.CreateSecurityGroupRequest;
import com.amazonaws.services.ec2.model.CreateSecurityGroupResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.DryRunResult;
import com.amazonaws.services.ec2.model.DryRunSupportedRequest;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceType;
import com.amazonaws.services.ec2.model.IpPermission;
import com.amazonaws.services.ec2.model.IpRange;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.elasticmapreduce.model.InstanceState;

public class TestAWS {

	@Test
	public void myStartAWS() {

		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA4ULUT3ZVII6VURSJ",
				"cNtA26jxPPLujOTDZE/xS/6XL0db05zUiCUCiRIl");

		AmazonEC2 client = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.AP_SOUTHEAST_1).build();

		// createSecurityGroup(client);

		// String key = createKey(client);

		// runInstance(client);

		StartInstancesRequest request = new StartInstancesRequest().withInstanceIds("i-0c620d6cf53fe8afe");

		// START INSTANCE
		// StartInstancesResult ins = client.startInstances(request);

		boolean done = false;
		List<String> setOfInstances = new ArrayList();
		setOfInstances.add("i-043b2e725d07ce079");
		// setOfInstances.add("i-043b2e725d07ce079");

		DescribeInstancesRequest descRequest = new DescribeInstancesRequest();
		descRequest.setInstanceIds(setOfInstances);

		DescribeInstancesResult response = client.describeInstances(descRequest);

		for (Reservation reservation : response.getReservations()) {
			for (Instance instance : reservation.getInstances()) {
				System.out.printf(
						"Found instance with id %s, " + "AMI %s, " + "type %s, " + "state %s "
								+ "and monitoring state %s at %s \n",
						instance.getInstanceId(), instance.getImageId(), instance.getInstanceType(),
						instance.getState().getName(), instance.getMonitoring().getState(),
						instance.getPublicIpAddress());

				System.out.println("Instance is IP: " + instance.getPublicIpAddress());
				System.out.println("STATE: " + instance.getState().getName().toUpperCase());
				System.out.println("CONST: " + InstanceState.RUNNING);

				if (instance.getState().getName().toUpperCase().equals(InstanceState.RUNNING.toString().toUpperCase()))
					System.out.println("Instance is running");
			}
		}

	}

	public void gitStart() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA4ULUT3ZVII6VURSJ",
				"cNtA26jxPPLujOTDZE/xS/6XL0db05zUiCUCiRIl");

		AmazonEC2 ec2 = AmazonEC2ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(Regions.AP_SOUTHEAST_1).build();

		String instance_id = "i-0c620d6cf53fe8afe";

		DryRunSupportedRequest<StartInstancesRequest> dry_request = () -> {
			StartInstancesRequest request = new StartInstancesRequest().withInstanceIds(instance_id);

			return request.getDryRunRequest();
		};

		DryRunResult dry_response = ec2.dryRun(dry_request);

		if (!dry_response.isSuccessful()) {
			System.out.printf("Failed dry run to start instance %s", instance_id);

			throw dry_response.getDryRunResponse();
		}

		StartInstancesRequest request = new StartInstancesRequest().withInstanceIds(instance_id);

		ec2.startInstances(request);

		System.out.printf("Successfully started instance %s", instance_id);
	}

	private void runInstance(AmazonEC2 client) {

		RunInstancesRequest runInstancesRequest = new RunInstancesRequest();

		runInstancesRequest.withImageId("ami-0c576d4e541a6937f").withInstanceType(InstanceType.T2Medium).withMinCount(1)
				.withMaxCount(1).withKeyName("DevSNPSeek").withSecurityGroups("SNPSeekGalaxy-JavaSecurityGroup2");

		RunInstancesResult result = client.runInstances(runInstancesRequest);

		Instance instance = result.getReservation().getInstances().get(0);

	}

	private String createKey(AmazonEC2 client) {
		CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();

		createKeyPairRequest.withKeyName("SNPseekRiceGalaxyKey");

		CreateKeyPairResult createKeyPairResult = client.createKeyPair(createKeyPairRequest);

		KeyPair keyPair = new KeyPair();

		keyPair = createKeyPairResult.getKeyPair();

		return keyPair.getKeyMaterial();

	}

	private void createSecurityGroup(AmazonEC2 client) {

		CreateSecurityGroupRequest csgr = new CreateSecurityGroupRequest();
		csgr.withGroupName("SNPSeekGalaxy-JavaSecurityGroup2")
				.withDescription("SNP-seek to start RiceSNP-seek galaxy instance");

		CreateSecurityGroupResult createSecurityGroupResult = client.createSecurityGroup(csgr);

		IpPermission ipPermission = new IpPermission();

		IpRange ipRange1 = new IpRange().withCidrIp("10.0.0.0/16");
		IpRange ipRange2 = new IpRange().withCidrIp("255.0.0.0/32");

		ipPermission.withIpv4Ranges(Arrays.asList(new IpRange[] { ipRange1, ipRange2 })).withIpProtocol("tcp")
				.withFromPort(22).withToPort(22);

		AuthorizeSecurityGroupIngressRequest authorizeSecurityGroupIngressRequest = new AuthorizeSecurityGroupIngressRequest();

		authorizeSecurityGroupIngressRequest.withGroupName("SNPSeekGalaxy-JavaSecurityGroup2")
				.withIpPermissions(ipPermission);

		client.authorizeSecurityGroupIngress(authorizeSecurityGroupIngressRequest);

	}

}
